package com.cc.customer.loan.service.usecases.createloanusecase;

public record FraudCheckResponse(String customerId, Boolean isFraud) {
}
