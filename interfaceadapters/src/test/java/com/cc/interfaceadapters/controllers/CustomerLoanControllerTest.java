package com.cc.interfaceadapters.controllers;

import com.cc.entities.Loan;
import com.cc.entities.LoanFactory;
import com.cc.entities.enums.LoanType;
import com.cc.usecases.createloanusecase.CreateLoanUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerLoanControllerTest {

    @Mock
    CreateLoanUseCase createLoanUseCase;

    @InjectMocks
    CustomerLoanController customerLoanController;

    @Test
    void Customer_loan_response_mapped_with_actual_loan_details() {
        var createLoanRequest = new CreateLoanRequest("12345", 12, 10000.00, 0.00, LoanType.HOUSING, "test system", Boolean.TRUE);
        when(createLoanUseCase.createLoan(any())).thenReturn(createSampleLoan());

        CreateLoanResponse createLoanResponse = customerLoanController.createLoan(createLoanRequest);

        assertNotNull(createLoanResponse);
        assertNotNull(createLoanResponse.loanStatus());
        assertNotNull(createLoanResponse.loanNumber());
        assertNotNull(createLoanResponse.customerId());
        assertNotNull(createLoanResponse.period());
    }

    private Loan createSampleLoan() {
        return LoanFactory.createLoan(LoanType.HOUSING, "12345", 12, 10000.00, 0.00);
    }
}
