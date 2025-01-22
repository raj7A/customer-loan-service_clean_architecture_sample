package com.cc.customer.loan.service.usecases.createloanusecase;

import com.cc.customer.loan.service.entities.Loan;

public interface CreateLoanUseCase {
    String createLoanUseCaseImpl = "CreateLoanUseCaseImpl";
    String createLoanUseCaseImplV1 = "CreateLoanUseCaseImplV1";

    Loan createLoan(LoanRequest loanRequest);
}
