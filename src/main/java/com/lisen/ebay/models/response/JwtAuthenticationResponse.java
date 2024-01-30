package com.lisen.ebay.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class JwtAuthenticationResponse {
    private String token;
}
