package com.cc.customer.loan.service.usecases.exceptions;

public class LoanSaveException extends RuntimeException {
    public LoanSaveException(String message) {
        super(message);
    }
}
