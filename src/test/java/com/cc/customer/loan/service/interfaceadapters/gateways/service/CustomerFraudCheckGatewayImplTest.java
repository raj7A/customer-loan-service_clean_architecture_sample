package com.cc.customer.loan.service.interfaceadapters.gateways.service;

import com.cc.customer.loan.service.usecases.createloanusecase.CustomerFraudCheckGateway;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerFraudCheckGatewayImplTest {

    @Autowired
    private CustomerFraudCheckGateway customerFraudCheckGateway;

    private static WireMockServer wireMockServer = new WireMockServer(8040);

    @BeforeAll
    static void setUp() {
        wireMockServer.start();
        configureFor("localhost", 8040);
    }

    @AfterAll
    static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void Customer_fails_the_fraud_check() {
        stubFor(WireMock.get(urlEqualTo("/fraudCheck/12345"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"customerId\":\"12345\",\"isFraud\":\"true\",\"trustScore\":\"10\"}")));

        var fraudCheckResponse = customerFraudCheckGateway.doCustomerFraudCheck("12345");

        assertNotNull(fraudCheckResponse);
        assertTrue(fraudCheckResponse.isFraud());
    }

    @Test
    void Customer_passes_the_fraud_check() {
        stubFor(WireMock.get(urlEqualTo("/fraudCheck/12345"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"customerId\":\"12345\",\"isFraud\":\"false\",\"trustScore\":\"10\"}")));

        var fraudCheckResponse = customerFraudCheckGateway.doCustomerFraudCheck("12345");

        assertNotNull(fraudCheckResponse);
        assertFalse(fraudCheckResponse.isFraud());
    }
}
