package com.cc.usecases;

import com.cc.entities.CarLoan;
import com.cc.entities.HomeLoan;
import com.cc.entities.enums.LoanType;
import com.cc.usecases.createloanusecase.*;
import com.cc.usecases.exceptions.CustomerFraudException;
import com.cc.usecases.exceptions.LoanSaveException;
import com.cc.usecases.properties.UseCaseProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CreateLoanUseCaseTest {

    @Mock
    private CustomerFraudCheckGateway customerFraudCheckGateway;
    @Mock
    private LoanGateway loanGateway;
    private CreateLoanUseCase createLoanUseCase;
    private final UseCaseProperties useCaseProperties = new UseCaseProperties(new HashMap<>());

    @BeforeEach
    void setUp() {
        HashMap<String, String> carLoanProperties = new HashMap<>();
        carLoanProperties.put("isEnabled", Boolean.TRUE.toString());
        useCaseProperties.feature().put("carLoan", carLoanProperties);
        createLoanUseCase = new CreateLoanUseCaseImpl(customerFraudCheckGateway, loanGateway, useCaseProperties);
    }

    @Test
    public void Car_loan_is_created_when_customer_passes_fraud_check() {
        var loanRequest = createLoanRequest(LoanType.CAR);
        Mockito.when(customerFraudCheckGateway.doCustomerFraudCheck(ArgumentMatchers.any())).thenReturn(new FraudCheckResponse("123", Boolean.FALSE));
        Mockito.when(loanGateway.saveLoan(any())).thenReturn(Boolean.TRUE);

        var carLoan = createLoanUseCase.createLoan(loanRequest);

        Assertions.assertNotNull(carLoan);
        Assertions.assertInstanceOf(CarLoan.class, carLoan);
    }

    @Test
    public void Home_loan_is_created_when_customer_passes_fraud_check() {
        var loanRequest = createLoanRequest(LoanType.HOUSING);
        Mockito.when(customerFraudCheckGateway.doCustomerFraudCheck(ArgumentMatchers.any())).thenReturn(new FraudCheckResponse("123", Boolean.FALSE));
        Mockito.when(loanGateway.saveLoan(ArgumentMatchers.any())).thenReturn(Boolean.TRUE);

        var homeLoan = createLoanUseCase.createLoan(loanRequest);

        Assertions.assertNotNull(homeLoan);
        Assertions.assertInstanceOf(HomeLoan.class, homeLoan);
    }

    @Test
    public void Car_loan_is_not_created_when_customer_fails_fraud_check() {
        var loanRequest = createLoanRequest(LoanType.CAR);
        Mockito.when(customerFraudCheckGateway.doCustomerFraudCheck(ArgumentMatchers.any())).thenReturn(new FraudCheckResponse("123", Boolean.TRUE));

        Assertions.assertThrows(CustomerFraudException.class, () -> createLoanUseCase.createLoan(loanRequest));
    }

    @Test
    public void Car_loan_is_not_created_when_loan_is_not_saved_in_data_store() {
        var loanRequest = createLoanRequest(LoanType.CAR);
        Mockito.when(customerFraudCheckGateway.doCustomerFraudCheck(ArgumentMatchers.any())).thenReturn(new FraudCheckResponse("123", Boolean.FALSE));
        Mockito.when(loanGateway.saveLoan(ArgumentMatchers.any())).thenReturn(Boolean.FALSE);

        Assertions.assertThrows(LoanSaveException.class, () -> createLoanUseCase.createLoan(loanRequest));
    }

    private LoanRequest createLoanRequest(LoanType loanType) {
        return new LoanRequest("123", 12, 1000000.00, 0.00, loanType);
    }

}
