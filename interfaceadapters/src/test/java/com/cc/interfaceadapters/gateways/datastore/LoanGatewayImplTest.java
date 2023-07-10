package com.cc.interfaceadapters.gateways.datastore;

import com.cc.entities.Loan;
import com.cc.entities.LoanFactory;
import com.cc.entities.enums.LoanType;
import com.cc.interfaceadapters.gateways.datastore.LoanDocument;
import com.cc.interfaceadapters.gateways.datastore.LoanGatewayImpl;
import com.cc.interfaceadapters.gateways.datastore.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class LoanGatewayImplTest {

    @Mock
    LoanRepository loanRepository;
    @InjectMocks
    private LoanGatewayImpl loanGateway;

    @Test
    public void Loan_gets_saved_to_the_datastore() {
        var homeLoan = LoanFactory.createLoan(LoanType.HOUSING, "123", 12, 1000000.00, 0.0);
        Mockito.when(loanRepository.save(any())).thenReturn(sampleLoanDocument(homeLoan));

        var isLoanSaved = loanGateway.saveLoan(homeLoan);

        assertTrue(isLoanSaved);
    }

    @Test
    public void Loan_not_saved_to_the_datastore_and_erred_out() {
        var homeLoan = LoanFactory.createLoan(LoanType.HOUSING, "123", 12, 1000000.00, 0.0);
        Mockito.when(loanRepository.save(any())).thenThrow(new RuntimeException("connection timeout"));

        assertThrows(Exception.class, () -> loanGateway.saveLoan(homeLoan));
    }

    private LoanDocument sampleLoanDocument(Loan loan) {
        LoanDocument loanDocument = new LoanDocument();

        loanDocument.setLoanNumber(loan.getLoanNumber());
        loanDocument.setCustomerId(loan.getCustomerId());
        loanDocument.setPeriod(loan.getPeriod());
        loanDocument.setRate(loan.getRate());
        loanDocument.setPrinciple(loan.getPrinciple());
        loanDocument.setOutStandingLoanAmount(loan.getOutStandingLoanAmount());
        loanDocument.setLoanType(loan.getLoanType());
        loanDocument.setStartDate(loan.getStartDate());
        loanDocument.setLoanStatus(loan.getLoanStatus());
        loanDocument.setInitialDiscountAmount(loan.getInitialDiscountAmount());

        return loanDocument;
    }

}
