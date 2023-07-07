package com.cc.customer.loan.service.interfaceadapters.gateways.datastore;

import com.cc.customer.loan.service.entities.enums.LoanStatus;
import com.cc.customer.loan.service.entities.enums.LoanType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Loan")
class LoanDocument {

    @Id
    private  String loanNumber;
    private  String customerId;
    private  Integer period;
    private  Double rate;
    private  Double principle;
    private  Double outStandingLoanAmount;
    private  LoanType loanType;
    private  LocalDate startDate;
    private  LoanStatus loanStatus;
    private  Double initialDiscountAmount;

    private final String loanCreatedSystem = "customer-loan";
    public LoanDocument() {
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getPrinciple() {
        return principle;
    }

    public void setPrinciple(Double principle) {
        this.principle = principle;
    }

    public Double getOutStandingLoanAmount() {
        return outStandingLoanAmount;
    }

    public void setOutStandingLoanAmount(Double outStandingLoanAmount) {
        this.outStandingLoanAmount = outStandingLoanAmount;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Double getInitialDiscountAmount() {
        return initialDiscountAmount;
    }

    public void setInitialDiscountAmount(Double initialDiscountAmount) {
        this.initialDiscountAmount = initialDiscountAmount;
    }

    public String getLoanCreatedSystem() {
        return loanCreatedSystem;
    }
}
