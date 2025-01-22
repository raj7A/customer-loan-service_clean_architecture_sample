package com.cc.customer.loan.service.usecases.createloanusecase;

import com.cc.customer.loan.service.entities.Loan;

public interface CreateLoanUseCase {
    String CreateLoanUseCase = "CreateLoanUseCase";
    String CreateLoanUseCaseV1 = "CreateLoanUseCaseV1";

    Loan createLoan(LoanRequest loanRequest);
}
