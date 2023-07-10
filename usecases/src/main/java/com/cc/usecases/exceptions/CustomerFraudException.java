package com.cc.usecases.exceptions;

public class CustomerFraudException extends RuntimeException {
    public CustomerFraudException(String message) {
        super(message);
    }
}
