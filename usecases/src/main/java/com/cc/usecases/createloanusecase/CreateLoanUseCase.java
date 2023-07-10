package com.cc.usecases.createloanusecase;

import com.cc.entities.Loan;

public interface CreateLoanUseCase {
    Loan createLoan(LoanRequest loanRequest);
}
