package com.cc.customer.loan.service.interfaceadapters.controllers;

import com.cc.customer.loan.service.entities.enums.LoanType;

public record CreateLoanRequest(String customerId, Integer period, Double principle, Double initialDiscountAmount, LoanType loanType, String originatingSystem, Boolean testTransaction) {
}
