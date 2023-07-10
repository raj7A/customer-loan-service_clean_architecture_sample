package com.cc.interfaceadapters.gateways.service;

import com.cc.usecases.createloanusecase.CustomerFraudCheckGateway;
import com.cc.usecases.createloanusecase.FraudCheckResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class CustomerFraudCheckGatewayImpl implements CustomerFraudCheckGateway {

    private final String resourceUrl = "http://localhost:8040/fraudCheck/{customerId}";
    private final RestTemplate restTemplate;

    public CustomerFraudCheckGatewayImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public FraudCheckResponse doCustomerFraudCheck(String customerId) {

        ResponseEntity<?> response;

        try {
            response = restTemplate.getForEntity(resourceUrl, FraudCheckServiceResponse.class, customerId);
        } catch (HttpStatusCodeException exception) {
            response = ResponseEntity.status(exception.getStatusCode()).headers(exception.getResponseHeaders())
                    .body(exception.getResponseBodyAsString());
        }

        if (!response.getStatusCode().is2xxSuccessful())
            throw new RuntimeException("Call failed");

        return toLoanUseCaseResponse((FraudCheckServiceResponse) response.getBody());
    }

    private FraudCheckResponse toLoanUseCaseResponse(FraudCheckServiceResponse fraudCheckServiceResponse) {
        return FraudCheckResponseMapper.FRAUD_CHECK_RESPONSE_MAPPER.fraudCheckServiceResponseToFraudCheckResponse(fraudCheckServiceResponse);
    }

    @Mapper
    public interface FraudCheckResponseMapper {
        FraudCheckResponseMapper FRAUD_CHECK_RESPONSE_MAPPER = Mappers.getMapper(FraudCheckResponseMapper.class);

        FraudCheckResponse fraudCheckServiceResponseToFraudCheckResponse(FraudCheckServiceResponse fraudCheckServiceResponse);
    }
}
