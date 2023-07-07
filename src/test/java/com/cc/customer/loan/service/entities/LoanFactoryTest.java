package com.cc.customer.loan.service.entities;

import com.cc.customer.loan.service.entities.enums.LoanType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoanFactoryTest {

    @Test
    public void Loan_factory_produces_car_loan_for_loan_type_car() {
        var carLoan = LoanFactory.createLoan(LoanType.CAR, "123", 12, 1000000.00, 0.00);

        Assertions.assertInstanceOf(CarLoan.class, carLoan);
    }

    @Test
    public void Loan_factory_produces_home_loan_for_loan_type_home() {
        var homeLoan = LoanFactory.createLoan(LoanType.HOUSING, "123", 12, 1000000.00, 0.00);

        Assertions.assertInstanceOf(HomeLoan.class, homeLoan);
    }
}
