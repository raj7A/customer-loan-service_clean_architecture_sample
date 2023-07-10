package com.cc.entities;

import com.cc.entities.Loan;
import com.cc.entities.LoanFactory;
import com.cc.entities.enums.LoanStatus;
import com.cc.entities.enums.LoanType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeLoanTest {

    @Test
    public void Home_loan_is_created_with_future_date_as_activation_date() {
        var homeLoan = createHomeLoan(0.00);

        Assertions.assertEquals(LoanStatus.CREATED, homeLoan.getLoanStatus());
        assertTrue(homeLoan.getStartDate().isAfter(LocalDate.now()));
    }

    @Test
    public void Home_loan_is_created_with_outstanding_amount_same_as_principle_amount_when_no_initial_discount_applied() {
        var initialDiscountAmount = 0.00;

        var homeLoan = createHomeLoan(initialDiscountAmount);

        assertEquals(homeLoan.getPrinciple(), homeLoan.getOutStandingLoanAmount());
    }

    @Test
    public void Home_loan_is_created_with_outstanding_amount_less_than_principle_amount_when_initial_discount_applied() {
        var initialDiscountAmount = 100000.00;

        var homeLoan = createHomeLoan(initialDiscountAmount);

        assertEquals(900000.00, homeLoan.getOutStandingLoanAmount());
    }

    @Test
    public void Customer_checks_the_monthly_payment_amount_to_be_made_against_the_home_loan_taken() {
        var initialDiscountAmount = 0.00;
        var homeLoan = createHomeLoan(initialDiscountAmount);

        var monthlyPaymentAmount = homeLoan.calculateMonthlyPaymentAmount();

        Assertions.assertEquals(50000.00, monthlyPaymentAmount);
    }

    private Loan createHomeLoan(double initialDiscountAmount) {
        return LoanFactory.createLoan(LoanType.HOUSING,"123", 12, 1000000.00, initialDiscountAmount);
    }
}
