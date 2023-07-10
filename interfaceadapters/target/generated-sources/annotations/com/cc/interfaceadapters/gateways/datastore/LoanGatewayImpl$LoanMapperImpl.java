package com.cc.interfaceadapters.gateways.datastore;

import com.cc.entities.Loan;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-10T13:34:51+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2.1 (Homebrew)"
)
public class LoanGatewayImpl$LoanMapperImpl implements LoanGatewayImpl.LoanMapper {

    @Override
    public LoanDocument loanDomainEntityToLoanDocument(Loan loan) {
        if ( loan == null ) {
            return null;
        }

        LoanDocument loanDocument = new LoanDocument();

        loanDocument.setLoanNumber( loan.getLoanNumber() );
        loanDocument.setCustomerId( loan.getCustomerId() );
        loanDocument.setPeriod( loan.getPeriod() );
        loanDocument.setRate( loan.getRate() );
        loanDocument.setPrinciple( loan.getPrinciple() );
        loanDocument.setOutStandingLoanAmount( loan.getOutStandingLoanAmount() );
        loanDocument.setLoanType( loan.getLoanType() );
        loanDocument.setStartDate( loan.getStartDate() );
        loanDocument.setLoanStatus( loan.getLoanStatus() );
        loanDocument.setInitialDiscountAmount( loan.getInitialDiscountAmount() );

        return loanDocument;
    }
}
