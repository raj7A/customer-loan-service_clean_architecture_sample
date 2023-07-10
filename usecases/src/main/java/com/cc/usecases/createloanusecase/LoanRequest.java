package com.cc.usecases.createloanusecase;

import com.cc.entities.enums.LoanType;

public record LoanRequest(String customerId, Integer period, Double principle, Double initialDiscountAmount, LoanType loanType) {
}
