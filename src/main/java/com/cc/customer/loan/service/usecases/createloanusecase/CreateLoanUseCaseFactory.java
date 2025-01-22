package com.cc.customer.loan.service.usecases.createloanusecase;

public interface CreateLoanUseCaseFactory {
  CreateLoanUseCase getCreateLoanUseCase(String useCaseName);
}
