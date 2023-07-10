package com.cc.configurations;

import com.cc.usecases.createloanusecase.LoanGateway;
import com.cc.interfaceadapters.gateways.datastore.LoanGatewayImpl;
import com.cc.interfaceadapters.gateways.datastore.LoanRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataStoreGatewayConfigs {

    @Bean
    protected LoanGateway loanGatewayBean(LoanRepository loanRepository) {
        return new LoanGatewayImpl(loanRepository);
    }

}
