package com.cc.interfaceadapters.controllers;

import com.cc.entities.enums.LoanType;

public record CreateLoanRequest(String customerId, Integer period, Double principle, Double initialDiscountAmount, LoanType loanType, String originatingSystem, Boolean testTransaction) {
}
