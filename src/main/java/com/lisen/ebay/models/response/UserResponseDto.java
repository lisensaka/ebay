package com.lisen.ebay.models.response;

import com.lisen.ebay.models.Role;

public record UserResponseDto(
        String fullName,
        String email,
        String password,
        Role role) {
}
