package com.lisen.ebay.models.request;

import com.lisen.ebay.models.Role;

public record SingUpModel(
        String fullName,
        String email,
        String password,
        Role role
) {}
