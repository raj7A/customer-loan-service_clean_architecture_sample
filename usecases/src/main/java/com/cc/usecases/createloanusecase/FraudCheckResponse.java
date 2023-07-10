package com.cc.usecases.createloanusecase;

public record FraudCheckResponse(String customerId, Boolean isFraud) {
}
