package com.lisen.ebay.exceptions;

public class UniqueEmailValidationException extends Exception {
    public UniqueEmailValidationException(String message) {
        super(message);
    }

    public UniqueEmailValidationException() {

    }
}
