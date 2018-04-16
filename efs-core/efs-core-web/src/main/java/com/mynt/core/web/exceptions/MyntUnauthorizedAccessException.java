package com.mynt.core.web.exceptions;

public class MyntUnauthorizedAccessException extends MyntInvalidTokenException {

    public MyntUnauthorizedAccessException(String message) {
        super(message);
    }
}
