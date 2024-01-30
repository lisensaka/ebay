package com.lisen.ebay.services.interfaces.impl;

import com.lisen.ebay.exceptions.CustomException;
import com.lisen.ebay.exceptions.ExceptionObject;
import com.lisen.ebay.exceptions.UniqueEmailValidationException;
import com.lisen.ebay.models.User;
import com.lisen.ebay.models.request.AuthenticationModel;
import com.lisen.ebay.models.request.SingUpModel;
import com.lisen.ebay.models.response.JwtAuthenticationResponse;
import com.lisen.ebay.repos.IUserRepository;
import com.lisen.ebay.services.interfaces.IAuthenticationService;
import com.lisen.ebay.services.interfaces.IJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SingUpModel request) throws UniqueEmailValidationException {
        if (getUserByEmailEqualsIgnoreCase(request.email()).isEmpty()) {
//            var user = User.builder().fullName(request.fullName())
//                    .email(request.email()).password(passwordEncoder.encode(request.password()))
//                    .role(Role.valueOf(request.role().name())).build();
            var user = new User(request.fullName(), request.email(), passwordEncoder.encode(request.password()), request.role());
            userRepository.save(user);
            //making users password null in order to not be included
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } else {
            throw new UniqueEmailValidationException(String.format("Email:'%s', you provided exists on DB, please choose new email and try again!", request.email()));
        }
    }

    @Override
    public JwtAuthenticationResponse signin(AuthenticationModel request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), (request.password())));
            var user = getUserByEmail(request);
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } catch (BadCredentialsException e) {
            throw new CustomException(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private User getUserByEmail(AuthenticationModel request) {
        return getUserByEmailEqualsIgnoreCase(request.email())
                .orElseThrow(() -> new NoSuchElementException(String.format("User with email: %s, was not found", request.email())));
    }

    private Optional<User> getUserByEmailEqualsIgnoreCase(String email) {
        return userRepository.findUserByEmailEqualsIgnoreCase(email);
    }
}
