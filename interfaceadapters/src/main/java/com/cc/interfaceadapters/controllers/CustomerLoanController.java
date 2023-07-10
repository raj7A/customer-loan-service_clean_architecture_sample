package com.cc.interfaceadapters.controllers;

import com.cc.entities.Loan;
import com.cc.usecases.createloanusecase.CreateLoanUseCase;
import com.cc.usecases.createloanusecase.LoanRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerLoanController {
    private final CreateLoanUseCase createLoanUseCase;

    public CustomerLoanController(CreateLoanUseCase createLoanUseCase) {
        this.createLoanUseCase = createLoanUseCase;
    }

    @PostMapping("/loan")
    public CreateLoanResponse createLoan(@RequestBody CreateLoanRequest createLoanRequest) {
        var loanRequest = LoanRequestResponseMapper.LOAN_REQUEST_MAPPER.loanCreateRequestToUseCaseLoanRequest(createLoanRequest);

        var loan = createLoanUseCase.createLoan(loanRequest);

        return LoanRequestResponseMapper.LOAN_REQUEST_MAPPER.loanEntityToLoanResponse(loan);
    }

    @Mapper
    public interface LoanRequestResponseMapper {
        LoanRequestResponseMapper LOAN_REQUEST_MAPPER = Mappers.getMapper(LoanRequestResponseMapper.class);

        LoanRequest loanCreateRequestToUseCaseLoanRequest(CreateLoanRequest createLoanRequest);

        CreateLoanResponse loanEntityToLoanResponse(Loan loan);
    }
}
