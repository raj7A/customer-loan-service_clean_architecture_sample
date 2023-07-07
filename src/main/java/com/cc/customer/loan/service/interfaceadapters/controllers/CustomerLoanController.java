package com.cc.customer.loan.service.interfaceadapters.controllers;

import com.cc.customer.loan.service.entities.Loan;
import com.cc.customer.loan.service.usecases.createloanusecase.CreateLoanUseCase;
import com.cc.customer.loan.service.usecases.createloanusecase.LoanRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.cc.customer.loan.service.interfaceadapters.controllers.CustomerLoanController.LoanRequestResponseMapper.LOAN_REQUEST_MAPPER;

@RestController
public class CustomerLoanController {
    private final CreateLoanUseCase createLoanUseCase;

    public CustomerLoanController(CreateLoanUseCase createLoanUseCase) {
        this.createLoanUseCase = createLoanUseCase;
    }

    @PostMapping("/loan")
    public CreateLoanResponse createLoan(@RequestBody CreateLoanRequest createLoanRequest) {
        var loanRequest = LOAN_REQUEST_MAPPER.loanCreateRequestToUseCaseLoanRequest(createLoanRequest);

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
