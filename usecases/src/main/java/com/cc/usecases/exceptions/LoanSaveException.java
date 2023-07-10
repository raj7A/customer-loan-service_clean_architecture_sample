package com.cc.usecases.exceptions;

public class LoanSaveException extends RuntimeException {
    public LoanSaveException(String message) {
        super(message);
    }
}
