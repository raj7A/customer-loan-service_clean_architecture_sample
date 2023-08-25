package com.cc.customer.loan.service.entities;

import com.cc.customer.loan.service.entities.enums.LoanType;

import java.time.LocalDate;

public class HomeLoan extends Loan {

    protected HomeLoan(String customerId, Integer period, Double principle, Double initialDiscountAmount) {
        super(customerId, LoanType.HOUSING, period, principle, initialDiscountAmount);
        //ObjectUtils.isEmpty(getLoanNumber());
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