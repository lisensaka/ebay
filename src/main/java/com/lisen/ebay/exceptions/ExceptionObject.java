package com.lisen.ebay.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ExceptionObject(
        String message,
        HttpStatus httpStatus,
        String dateTime
) {
}
