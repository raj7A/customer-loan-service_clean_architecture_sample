package com.cc.customer.loan.service.interfaceadapters.controllers;

import com.cc.customer.loan.service.usecases.createloanusecase.CreateLoanUseCaseFactory;
import com.cc.customer.loan.service.entities.Loan;
import com.cc.customer.loan.service.usecases.createloanusecase.LoanRequest;
import com.cc.customer.loan.service.usecases.properties.UseCaseProperties;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.cc.customer.loan.service.interfaceadapters.controllers.CustomerLoanController.LoanRequestResponseMapper.LOAN_REQUEST_MAPPER;
import static com.cc.customer.loan.service.usecases.createloanusecase.CreateLoanUseCase.*;

@RestController
public class CustomerLoanController {
    private final CreateLoanUseCaseFactory createLoanUseCaseFactory;
    private final UseCaseProperties useCaseProperties;

    public CustomerLoanController(CreateLoanUseCaseFactory createLoanUseCaseFactory, UseCaseProperties useCaseProperties) {
        this.createLoanUseCaseFactory = createLoanUseCaseFactory;
        this.useCaseProperties = useCaseProperties;
    }

    @PostMapping("/loan")
    public CreateLoanResponse createLoan(@RequestBody CreateLoanRequest createLoanRequest) {
        var loanRequest = LOAN_REQUEST_MAPPER.loanCreateRequestToUseCaseLoanRequest(createLoanRequest);

        var createLoanUseCase = createLoanUseCaseFactory.getCreateLoanUseCase(
                useCaseProperties.newFlowEnabled() ? createLoanUseCaseImplV1 : createLoanUseCaseImpl);
        var loan = createLoanUseCase.createLoan(loanRequest);

        return LOAN_REQUEST_MAPPER.loanEntityToLoanResponse(loan);
    }

    @Mapper
    public interface LoanRequestResponseMapper {
        LoanRequestResponseMapper LOAN_REQUEST_MAPPER = Mappers.getMapper(LoanRequestResponseMapper.class);

        LoanRequest loanCreateRequestToUseCaseLoanRequest(CreateLoanRequest createLoanRequest);

        CreateLoanResponse loanEntityToLoanResponse(Loan loan);
    }
}
