package com.cc.configurations;

import com.cc.usecases.createloanusecase.CreateLoanUseCase;
import com.cc.usecases.createloanusecase.CreateLoanUseCaseImpl;
import com.cc.usecases.createloanusecase.CustomerFraudCheckGateway;
import com.cc.usecases.createloanusecase.LoanGateway;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfigs {

    @Bean
    public CreateLoanUseCase createLoanUseCaseBean(CustomerFraudCheckGateway customerFraudCheckGateway, LoanGateway loanGateway, UseCaseProperties useCaseProperties) {
        return new CreateLoanUseCaseImpl(customerFraudCheckGateway, loanGateway, ConfigMapper.CONFIG_MAPPER.toDomainConfig(useCaseProperties));
    }

    @Mapper
    public interface ConfigMapper {
        ConfigMapper CONFIG_MAPPER = Mappers.getMapper(ConfigMapper.class);

        com.cc.usecases.properties.UseCaseProperties toDomainConfig(UseCaseProperties useCaseProperties);

    }

}