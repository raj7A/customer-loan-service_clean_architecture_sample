package com.cc.usecases.createloanusecase;

import com.cc.entities.Loan;
import com.cc.entities.LoanFactory;
import com.cc.usecases.exceptions.CustomerFraudException;
import com.cc.usecases.exceptions.LoanSaveException;
import com.cc.usecases.properties.UseCaseProperties;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CreateLoanUseCaseImpl implements CreateLoanUseCase {

    private final CustomerFraudCheckGateway customerFraudCheckGateway;
    private final LoanGateway loanGateway;
    private final UseCaseProperties useCaseProperties;

    private static final Logger logger = LogManager.getLogManager().getLogger(CreateLoanUseCaseImpl.class.getName());

    public CreateLoanUseCaseImpl(CustomerFraudCheckGateway customerFraudCheckService, LoanGateway loanGateway, UseCaseProperties useCaseProperties) {
        this.customerFraudCheckGateway = customerFraudCheckService;
        this.loanGateway = loanGateway;
        this.useCaseProperties = useCaseProperties;
    }

    @Override
    public Loan createLoan(LoanRequest loanRequest) {
        doFraudCheck(loanRequest.customerId());
        var loan = doCreateLoan(loanRequest);
        saveLoan(loan);
        return loan;
    }

    private Loan doCreateLoan(LoanRequest loanRequest) {
        return LoanFactory.createLoan(loanRequest.loanType(), loanRequest.customerId(), loanRequest.period(), loanRequest.principle(), loanRequest.initialDiscountAmount());
    }

    private void saveLoan(Loan loan) throws LoanSaveException {
        var isLoanSaved = loanGateway.saveLoan(loan);
        if (!isLoanSaved)
            throw new LoanSaveException("Error occurred while saving the loan in data store");
    }

    private void doFraudCheck(String customerId) throws CustomerFraudException {
        var fraudCheckResponse = customerFraudCheckGateway.doCustomerFraudCheck(customerId);
        if (fraudCheckResponse.isFraud())
            throw new CustomerFraudException("Customer failed the fraud check");
    }

}
