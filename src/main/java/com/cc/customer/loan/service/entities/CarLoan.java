package com.cc.customer.loan.service.entities;

import com.cc.customer.loan.service.entities.enums.LoanType;

public class CarLoan extends Loan {

    protected CarLoan(String customerId, Integer period, Double principle, Double initialDiscountAmount) {
        super(customerId, LoanType.CAR, period, principle, initialDiscountAmount);
        activateLoan();
    }

    @Override
    protected Double getInterestRatePercentage() {
        return 10.00;
    }

}
