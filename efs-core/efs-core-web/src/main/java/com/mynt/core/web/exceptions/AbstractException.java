package com.mynt.core.web.exceptions;

public abstract class AbstractException extends RuntimeException {

    public AbstractException(String message) {
        super(message);
    }
}
