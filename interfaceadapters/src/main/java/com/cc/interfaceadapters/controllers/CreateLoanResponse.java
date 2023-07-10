package com.cc.interfaceadapters.controllers;

import com.cc.entities.enums.LoanStatus;

public record CreateLoanResponse(String loanNumber, String customerId, Integer period, LoanStatus loanStatus){
}
