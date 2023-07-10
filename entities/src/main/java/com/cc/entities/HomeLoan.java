package com.cc.entities;

import com.cc.entities.enums.LoanType;

import java.time.LocalDate;

public class HomeLoan extends Loan {

    protected HomeLoan(String customerId, Integer period, Double principle, Double initialDiscountAmount) {
        super(customerId, LoanType.HOUSING, period, principle, initialDiscountAmount);
    }

    @Override
    protected Double getInterestRatePercentage() {
        return 5.00;
    }

    @Override
    protected LocalDate calculateStartDate() {
        return super.calculateStartDate().plusDays(1);
    }


}