package com.lisen.ebay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalRestResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<?> NoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getExceptionObjectBuild(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(value = {UniqueEmailValidationException.class})
    public ResponseEntity<?> UniqueEmailValidationException(UniqueEmailValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getExceptionObjectBuild(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<?> CustomException(CustomException e) {
        return ResponseEntity.status(e.getHttpStatusCode()).body(getExceptionObjectBuild(e.getMessage(), e.getHttpStatusCode()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> Exception(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getExceptionObjectBuild(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private static ExceptionObject getExceptionObjectBuild(String message, HttpStatus httpStatusCode) {
        return ExceptionObject.builder()
                .message(message)
                .httpStatus(httpStatusCode)
                .dateTime(LocalDateTime.now().toString())
                .build();
    }


}
