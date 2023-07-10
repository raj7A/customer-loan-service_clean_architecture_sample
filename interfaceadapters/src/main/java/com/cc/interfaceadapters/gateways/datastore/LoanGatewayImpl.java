package com.cc.interfaceadapters.gateways.datastore;

import com.cc.entities.Loan;
import com.cc.usecases.createloanusecase.LoanGateway;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public class LoanGatewayImpl implements LoanGateway {

    private final LoanRepository loanRepository;

    public LoanGatewayImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Boolean saveLoan(Loan loan) {
        var loanDocument = toLoanRepositoryDocument(loan);

        try {
            loanRepository.save(loanDocument);
            return Boolean.TRUE;
        } catch (Exception exception) {
            throw new RuntimeException("Exception while saving");
        }
    }

    private LoanDocument toLoanRepositoryDocument(Loan loan) {
        return LoanMapper.LOAN_MAPPER.loanDomainEntityToLoanDocument(loan);
    }

    @Mapper
    public interface LoanMapper {
        LoanMapper LOAN_MAPPER = Mappers.getMapper(LoanMapper.class);

        LoanDocument loanDomainEntityToLoanDocument(Loan loan);
    }
}
