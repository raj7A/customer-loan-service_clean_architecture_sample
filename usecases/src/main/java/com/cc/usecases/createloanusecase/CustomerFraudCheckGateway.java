package com.cc.usecases.createloanusecase;

public interface CustomerFraudCheckGateway {
    FraudCheckResponse doCustomerFraudCheck(String customerId);
}
