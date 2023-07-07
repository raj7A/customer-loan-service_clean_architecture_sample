package com.cc.customer.loan.service.interfaceadapters.gateways.datastore;

import com.cc.customer.loan.service.entities.LoanFactory;
import com.cc.customer.loan.service.entities.enums.LoanType;
import com.cc.customer.loan.service.usecases.createloanusecase.LoanGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoanGatewayImplTest {

    @Autowired
    private LoanGateway loanGateway;

    @Test
    public void Loan_gets_saved_to_the_datastore() {
        var homeLoan = LoanFactory.createLoan(LoanType.HOUSING,"123", 12, 1000000.00, 0.0);

        var isLoanSaved = loanGateway.saveLoan(homeLoan);

        assertTrue(isLoanSaved);
    }

}
