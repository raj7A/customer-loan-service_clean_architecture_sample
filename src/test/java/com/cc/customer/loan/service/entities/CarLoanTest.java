package com.cc.customer.loan.service.entities;

import com.cc.customer.loan.service.entities.enums.LoanStatus;
import com.cc.customer.loan.service.entities.enums.LoanType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarLoanTest {

    @Test
    public void Car_loan_is_activated_on_current_created_date() {
        var carLoan = createCarLoan(0.00);

        assertEquals(LoanStatus.ACTIVE, carLoan.getLoanStatus());
        assertTrue(carLoan.getStartDate().isEqual(LocalDate.now()));
    }

    @Test
    public void Car_loan_is_created_with_outstanding_amount_same_as_principle_when_no_initial_discount_applied() {
        var initialDiscountAmount = 0.00;

        var carLoan = createCarLoan(initialDiscountAmount);

        assertEquals(carLoan.getPrinciple(), carLoan.getOutStandingLoanAmount());
    }

    @Test
    public void Car_loan_is_created_with_outstanding_amount_less_than_principle_when_initial_discount_applied() {
        var initialDiscountAmount = 100000.00;

        var carLoan = createCarLoan(initialDiscountAmount);

        assertEquals(900000.00, carLoan.getOutStandingLoanAmount());
    }

    @Test
    public void Customer_checks_the_monthly_payment_amount_to_be_made_against_the_car_loan_taken() {
        var initialDiscountAmount = 100000.00;
        var carLoan = createCarLoan(initialDiscountAmount);

        var monthlyPaymentAmount = carLoan.calculateMonthlyPaymentAmount();

        assertEquals(100000.00, monthlyPaymentAmount);
    }

    private Loan createCarLoan(double initialDiscountAmount) {
        return LoanFactory.createLoan(LoanType.CAR,"123", 12, 1000000.00, initialDiscountAmount);
    }

}
