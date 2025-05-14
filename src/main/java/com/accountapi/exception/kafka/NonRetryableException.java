package com.accountapi.exception.kafka;

public class NonRetryableException extends RuntimeException{
    public NonRetryableException(Throwable cause) {
        super(cause);
    }
}