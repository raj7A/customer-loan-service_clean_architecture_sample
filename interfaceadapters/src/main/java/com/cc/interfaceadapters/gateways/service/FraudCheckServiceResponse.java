package com.cc.interfaceadapters.gateways.service;

public record FraudCheckServiceResponse(String customerId, Boolean isFraud, Integer trustScore) {
}
