package com.lisen.ebay.controllers;

import com.lisen.ebay.exceptions.UniqueEmailValidationException;
import com.lisen.ebay.models.request.AuthenticationModel;
import com.lisen.ebay.models.request.SingUpModel;
import com.lisen.ebay.models.response.JwtAuthenticationResponse;
import com.lisen.ebay.services.interfaces.IAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
//@CrossOrigin("http://localhost:4200")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    public AuthenticationController( IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> createUser(@RequestBody SingUpModel singUpModel) throws UniqueEmailValidationException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.signup(singUpModel));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> loginUser(@RequestBody AuthenticationModel authenticationModel) {
//        userService.createUser(singUpModel);
        log.info("Login request successfully from user {} ", authenticationModel);
        return ResponseEntity.ok().body(authenticationService.signin(authenticationModel));
    }
}
