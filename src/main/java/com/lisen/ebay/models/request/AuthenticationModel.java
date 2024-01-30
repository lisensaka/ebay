package com.lisen.ebay.models.request;

public record AuthenticationModel(
        String email,
        String password
) {
}
