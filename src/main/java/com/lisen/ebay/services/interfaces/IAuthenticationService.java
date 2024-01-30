package com.lisen.ebay.services.interfaces;

import com.lisen.ebay.exceptions.UniqueEmailValidationException;
import com.lisen.ebay.models.request.AuthenticationModel;
import com.lisen.ebay.models.request.SingUpModel;
import com.lisen.ebay.models.response.JwtAuthenticationResponse;

public interface IAuthenticationService {
    JwtAuthenticationResponse signup(SingUpModel request) throws UniqueEmailValidationException, UniqueEmailValidationException;

    JwtAuthenticationResponse signin(AuthenticationModel request);
}
