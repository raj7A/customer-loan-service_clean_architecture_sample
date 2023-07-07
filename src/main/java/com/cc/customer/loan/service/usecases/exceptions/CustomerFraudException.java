package com.cc.customer.loan.service.usecases.exceptions;

public class CustomerFraudException extends RuntimeException {
    public CustomerFraudException(String message) {
        super(message);
    }
}
