package com.cc.customer.loan.service.usecases.createloanusecase;

import com.cc.customer.loan.service.entities.enums.LoanType;

public record LoanRequest(String customerId, Integer period, Double principle, Double initialDiscountAmount, LoanType loanType) {
}
