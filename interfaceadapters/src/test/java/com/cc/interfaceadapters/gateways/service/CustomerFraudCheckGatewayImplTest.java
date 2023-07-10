package com.cc.interfaceadapters.gateways.service;

import com.cc.interfaceadapters.gateways.service.CustomerFraudCheckGatewayImpl;
import com.cc.interfaceadapters.gateways.service.FraudCheckServiceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CustomerFraudCheckGatewayImplTest {

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    private CustomerFraudCheckGatewayImpl customerFraudCheckGateway;

    @Test
    void Customer_fraud_check_response_is_mapped_as_expected_when_endpoint_respond_with_2xx_status() {
        Mockito.when(restTemplate.getForEntity(any(), any(), (Object) any())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(sampleFraudCheckResponse()));
        var fraudCheckResponse = customerFraudCheckGateway.doCustomerFraudCheck("12345");

        assertNotNull(fraudCheckResponse);
        assertNotNull(fraudCheckResponse.isFraud());
        assertNotNull(fraudCheckResponse.customerId());
    }

    @Test
    void Customer_fraud_check_erred_out_when_endpoint_respond_with_5xx_status() {
        Mockito.when(restTemplate.getForEntity(any(), any(), (Object) any())).thenReturn(ResponseEntity.internalServerError().body("Call failed"));
        assertThrows(Exception.class, () -> customerFraudCheckGateway.doCustomerFraudCheck("12345"));
    }

    private FraudCheckServiceResponse sampleFraudCheckResponse() {
        return new FraudCheckServiceResponse("12345", Boolean.TRUE, 10);
    }
}
