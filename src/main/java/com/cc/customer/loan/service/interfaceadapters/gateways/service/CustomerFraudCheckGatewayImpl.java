package com.cc.customer.loan.service.interfaceadapters.gateways.service;

import com.cc.customer.loan.service.usecases.createloanusecase.CustomerFraudCheckGateway;
import com.cc.customer.loan.service.usecases.createloanusecase.FraudCheckResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.client.RestTemplate;

import static com.cc.customer.loan.service.interfaceadapters.gateways.service.CustomerFraudCheckGatewayImpl.FraudCheckResponseMapper.FRAUD_CHECK_RESPONSE_MAPPER;

public class CustomerFraudCheckGatewayImpl implements CustomerFraudCheckGateway {

    private final String resourceUrl = "http://localhost:8040/fraudCheck/{customerId}";
    private final RestTemplate restTemplate;

    public CustomerFraudCheckGatewayImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public FraudCheckResponse doCustomerFraudCheck(String customerId) {

        var response
                = restTemplate.getForEntity(resourceUrl, FraudCheckServiceResponse.class, customerId);

        if (!response.getStatusCode().is2xxSuccessful())
            throw new RuntimeException("Downstream down");

        return toLoanUseCaseResponse(response.getBody());
    }

    private FraudCheckResponse toLoanUseCaseResponse(FraudCheckServiceResponse fraudCheckServiceResponse) {
        return FRAUD_CHECK_RESPONSE_MAPPER.fraudCheckServiceResponseToFraudCheckResponse(fraudCheckServiceResponse);
    }

    @Mapper
    public interface FraudCheckResponseMapper {
        FraudCheckResponseMapper FRAUD_CHECK_RESPONSE_MAPPER = Mappers.getMapper(FraudCheckResponseMapper.class);

        FraudCheckResponse fraudCheckServiceResponseToFraudCheckResponse(FraudCheckServiceResponse fraudCheckServiceResponse);
    }
}
