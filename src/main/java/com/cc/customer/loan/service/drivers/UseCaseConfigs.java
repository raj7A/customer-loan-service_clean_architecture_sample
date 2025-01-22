package com.cc.customer.loan.service.drivers;

import com.cc.customer.loan.service.usecases.createloanusecase.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.cc.customer.loan.service.drivers.UseCaseConfigs.ConfigMapper.CONFIG_MAPPER;

@Configuration
public class UseCaseConfigs {

    @Bean(value = CreateLoanUseCase.createLoanUseCaseImpl)
    public CreateLoanUseCase createLoanUseCaseBean(CustomerFraudCheckGateway customerFraudCheckGateway, LoanGateway loanGateway, com.cc.customer.loan.service.usecases.properties.UseCaseProperties useCaseProperties) {
        return new CreateLoanUseCaseImpl(customerFraudCheckGateway, loanGateway);
    }

    @Bean(value = CreateLoanUseCase.createLoanUseCaseImplV1)
    public CreateLoanUseCase createLoanUseCaseV1(CustomerFraudCheckGateway customerFraudCheckGateway, LoanGateway loanGateway, com.cc.customer.loan.service.usecases.properties.UseCaseProperties useCaseProperties) {
        return new CreateLoanUseCaseImplV1(customerFraudCheckGateway, loanGateway);
    }

    @Bean
    public com.cc.customer.loan.service.usecases.properties.UseCaseProperties createUseCaseProperties(UseCaseProperties useCaseProperties) {
        return CONFIG_MAPPER.toDomainConfig(useCaseProperties);
    }

    @Bean
    public ServiceLocatorFactoryBean getFulfilmentCheckProcessorFactoryBean() {
        ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
        serviceLocatorFactoryBean.setServiceLocatorInterface(CreateLoanUseCaseFactory.class);
        return serviceLocatorFactoryBean;
    }

    @Mapper
    public interface ConfigMapper {
        UseCaseConfigs.ConfigMapper CONFIG_MAPPER = Mappers.getMapper(UseCaseConfigs.ConfigMapper.class);

        com.cc.customer.loan.service.usecases.properties.UseCaseProperties toDomainConfig(UseCaseProperties useCaseProperties);

    }

}