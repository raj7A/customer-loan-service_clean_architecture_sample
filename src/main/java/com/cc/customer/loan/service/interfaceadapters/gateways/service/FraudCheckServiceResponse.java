package com.cc.customer.loan.service.interfaceadapters.gateways.service;

public record FraudCheckServiceResponse(String customerId, Boolean isFraud, Integer trustScore) {
}
