package com.cc.usecases.createloanusecase;

import com.cc.entities.Loan;

public interface LoanGateway {
    Boolean saveLoan(Loan loan);
}
