package com.cc.homeloan;

import com.cc.interfaceadapters.controllers.CreateLoanRequest;
import com.cc.interfaceadapters.controllers.CreateLoanResponse;
import com.cc.entities.enums.LoanStatus;
import com.cc.entities.enums.LoanType;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateCustomerLoanTest {

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
    void Customer_creates_the_home_loan_for_12_months_period_that_is_not_yet_activated() {
        var createLoanRequest = new CreateLoanRequest("12345", 12, 10000.00, 0.00, LoanType.HOUSING, "test system", Boolean.TRUE);
        stubFor(WireMock.get(urlEqualTo("/fraudCheck/12345"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"customerId\":\"12345\",\"isFraud\":\"false\",\"trustScore\":\"10\"}")));

        var createLoanResponseResponseEntity = testRestTemplate.postForEntity("/loan", createLoanRequest, CreateLoanResponse.class);
        HttpStatusCode statusCode = createLoanResponseResponseEntity.getStatusCode();
        CreateLoanResponse createLoanResponse = createLoanResponseResponseEntity.getBody();
        assertEquals(OK, statusCode);
        assertEquals(LoanStatus.CREATED, createLoanResponse.loanStatus());
        assertEquals(12, createLoanResponse.period());
        assertNotNull(createLoanResponse.loanNumber());
    }

    @Test
    void Customer_is_not_able_to_create_home_loan_when_fails_the_fraud_check() {
        var createLoanRequest = new CreateLoanRequest("12345", 12, 10000.00, 0.00, LoanType.HOUSING, "test system", Boolean.TRUE);
        stubFor(WireMock.get(urlEqualTo("/fraudCheck/12345"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"customerId\":\"12345\",\"isFraud\":\"true\",\"trustScore\":\"10\"}")));

        var createLoanResponseResponseEntity = testRestTemplate.postForEntity("/loan", createLoanRequest, CreateLoanResponse.class);

        assertEquals(INTERNAL_SERVER_ERROR, createLoanResponseResponseEntity.getStatusCode());
    }
}
