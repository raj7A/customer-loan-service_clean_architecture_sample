package com.cc.interfaceadapters.controllers;

import com.cc.entities.Loan;
import com.cc.entities.enums.LoanStatus;
import com.cc.entities.enums.LoanType;
import com.cc.usecases.createloanusecase.LoanRequest;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-10T13:34:51+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2.1 (Homebrew)"
)
public class CustomerLoanController$LoanRequestResponseMapperImpl implements CustomerLoanController.LoanRequestResponseMapper {

    @Override
    public LoanRequest loanCreateRequestToUseCaseLoanRequest(CreateLoanRequest createLoanRequest) {
        if ( createLoanRequest == null ) {
            return null;
        }

        String customerId = null;
        Integer period = null;
        Double principle = null;
        Double initialDiscountAmount = null;
        LoanType loanType = null;

        customerId = createLoanRequest.customerId();
        period = createLoanRequest.period();
        principle = createLoanRequest.principle();
        initialDiscountAmount = createLoanRequest.initialDiscountAmount();
        loanType = createLoanRequest.loanType();

        LoanRequest loanRequest = new LoanRequest( customerId, period, principle, initialDiscountAmount, loanType );

        return loanRequest;
    }

    @Override
    public CreateLoanResponse loanEntityToLoanResponse(Loan loan) {
        if ( loan == null ) {
            return null;
        }

        String loanNumber = null;
        String customerId = null;
        Integer period = null;
        LoanStatus loanStatus = null;

        loanNumber = loan.getLoanNumber();
        customerId = loan.getCustomerId();
        period = loan.getPeriod();
        loanStatus = loan.getLoanStatus();

        CreateLoanResponse createLoanResponse = new CreateLoanResponse( loanNumber, customerId, period, loanStatus );

        return createLoanResponse;
    }
}
