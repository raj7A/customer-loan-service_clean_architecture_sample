package com.cc.customer.loan.service.interfaceadapters.controllers;

import com.cc.customer.loan.service.entities.enums.LoanStatus;

public record CreateLoanResponse(String loanNumber, String customerId, Integer period, LoanStatus loanStatus){
}
