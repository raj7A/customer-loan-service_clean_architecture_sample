package com.cc.customer.loan.service.usecases.createloanusecase;

import com.cc.customer.loan.service.entities.Loan;

public interface LoanGateway {
    Boolean saveLoan(Loan loan);
}
