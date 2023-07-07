package com.cc.customer.loan.service.configurations;

import com.cc.customer.loan.service.usecases.createloanusecase.CustomerFraudCheckGateway;
import com.cc.customer.loan.service.interfaceadapters.gateways.service.CustomerFraudCheckGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceGatewayConfigs {

    @Bean
    public CustomerFraudCheckGateway customerFraudCheckGatewayBean(RestTemplate restTemplate) {
        return new CustomerFraudCheckGatewayImpl(restTemplate);
    }

    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }

}
