package com.cc.controllers;

import com.cc.entities.enums.LoanType;
import com.cc.interfaceadapters.controllers.CreateLoanRequest;
import com.cc.interfaceadapters.controllers.CreateLoanResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerLoanControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

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
    void Endpoint_for_create_loan_does_create_the_loan_and_responds_with_2xx_status() {
        var createLoanRequest = new CreateLoanRequest("12345", 12, 10000.00, 0.00, LoanType.HOUSING, "test system", Boolean.TRUE);
        stubFor(WireMock.get(urlEqualTo("/fraudCheck/12345"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"customerId\":\"12345\",\"isFraud\":\"false\",\"trustScore\":\"10\"}")));

        var createLoanResponseResponseEntity = testRestTemplate.postForEntity("/loan", createLoanRequest, CreateLoanResponse.class);

        assertEquals(HttpStatus.OK, createLoanResponseResponseEntity.getStatusCode());
        assertNotNull(createLoanResponseResponseEntity.getBody());
    }

    @Test
    void Endpoint_for_create_loan_does_not_create_the_loan_and_responds_with_5xx_status() {
        var createLoanRequest = new CreateLoanRequest("12345", 12, 10000.00, 0.00, LoanType.HOUSING, "test system", Boolean.TRUE);
        stubFor(WireMock.get(urlEqualTo("/fraudCheck/12345"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"customerId\":\"12345\",\"isFraud\":\"true\",\"trustScore\":\"10\"}")));

        var createLoanResponseResponseEntity = testRestTemplate.postForEntity("/loan", createLoanRequest, CreateLoanResponse.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, createLoanResponseResponseEntity.getStatusCode());
    }
}
