package com.cc.customer.loan.service.drivers;

import com.cc.customer.loan.service.usecases.createloanusecase.LoanGateway;
import com.cc.customer.loan.service.interfaceadapters.gateways.datastore.LoanGatewayImpl;
import com.cc.customer.loan.service.interfaceadapters.gateways.datastore.LoanRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataStoreGatewayConfigs {

    @Bean
    public LoanGateway loanGatewayBean(LoanRepository loanRepository) {
        return new LoanGatewayImpl(loanRepository);
    }

}
