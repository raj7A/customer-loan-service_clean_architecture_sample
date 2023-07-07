package com.cc.customer.loan.service.configurations;

import com.cc.customer.loan.service.usecases.createloanusecase.CreateLoanUseCase;
import com.cc.customer.loan.service.usecases.createloanusecase.CreateLoanUseCaseImpl;
import com.cc.customer.loan.service.usecases.createloanusecase.CustomerFraudCheckGateway;
import com.cc.customer.loan.service.usecases.createloanusecase.LoanGateway;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.cc.customer.loan.service.configurations.UseCaseConfigs.ConfigMapper.CONFIG_MAPPER;

@Configuration
public class UseCaseConfigs {

    @Bean
    public CreateLoanUseCase createLoanUseCaseBean(CustomerFraudCheckGateway customerFraudCheckGateway, LoanGateway loanGateway, UseCaseProperties useCaseProperties) {
        return new CreateLoanUseCaseImpl(customerFraudCheckGateway, loanGateway, CONFIG_MAPPER.toDomainConfig(useCaseProperties));
    }

    @Mapper
    public interface ConfigMapper {
        UseCaseConfigs.ConfigMapper CONFIG_MAPPER = Mappers.getMapper(UseCaseConfigs.ConfigMapper.class);

        com.cc.customer.loan.service.usecases.properties.UseCaseProperties toDomainConfig(UseCaseProperties useCaseProperties);

    }

}