package com.accountapi.exception.kafka;

public class RetryableException extends RuntimeException{
    public RetryableException(Throwable cause) {
        super(cause);
    }
}
