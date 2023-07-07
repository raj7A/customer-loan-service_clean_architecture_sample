package com.cc.customer.loan.service.usecases.createloanusecase;

public interface CustomerFraudCheckGateway {
    FraudCheckResponse doCustomerFraudCheck(String customerId);
}
