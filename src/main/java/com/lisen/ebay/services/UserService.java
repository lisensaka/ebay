package com.lisen.ebay.services;

import com.lisen.ebay.mapper.ICustomMapper;
import com.lisen.ebay.models.User;
import com.lisen.ebay.models.request.SingUpModel;
import com.lisen.ebay.models.response.UserResponseDto;
import com.lisen.ebay.repos.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final IUserRepository iUserRepository;
//    private final PasswordEncoder passwordEncoder;

    private final static ICustomMapper customMapper = Mappers.getMapper(ICustomMapper.class);


    public UserResponseDto createUser(SingUpModel singUpModel) {
        User user = convertSignUpModeToUser(singUpModel);
//        user.setPassword(passwordEncoder.encode(singUpModel.password()));
        User savedUser = iUserRepository.save(user);
        return customMapper.convertUserToUserResponseDto(savedUser);
    }

    private static User convertSignUpModeToUser(SingUpModel singUpModel) {
        return customMapper.convertSignUpModelToUser(singUpModel);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iUserRepository.findUserByEmailEqualsIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("Username: %s , not found", username))
                );
    }
}
