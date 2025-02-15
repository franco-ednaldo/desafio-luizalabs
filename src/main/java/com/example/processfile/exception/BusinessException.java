package com.example.processfile.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(final String message, Throwable ex) {
        super(message, ex);
    }
    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException() {
        super();
    }

}
