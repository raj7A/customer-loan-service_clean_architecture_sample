package com.cc.gateways.service;

import com.cc.usecases.createloanusecase.CustomerFraudCheckGateway;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    void Customer_fraud_check_succeeds_when_endpoint_respond_with_2xx_status() {
        stubFor(WireMock.get(urlEqualTo("/fraudCheck/12345"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"customerId\":\"12345\",\"isFraud\":\"true\",\"trustScore\":\"10\"}")));

        var fraudCheckResponse = customerFraudCheckGateway.doCustomerFraudCheck("12345");

        assertNotNull(fraudCheckResponse);
        assertTrue(fraudCheckResponse.isFraud());
    }

    @Test
    void Customer_fraud_check_erred_out_when_endpoint_respond_with_5xx_status() {
        stubFor(WireMock.get(urlEqualTo("/fraudCheck/12345"))
                .willReturn(serverError()));

        assertThrows(Exception.class, () -> customerFraudCheckGateway.doCustomerFraudCheck("12345"));
    }
}
