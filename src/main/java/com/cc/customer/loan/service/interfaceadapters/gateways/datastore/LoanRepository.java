package com.cc.customer.loan.service.interfaceadapters.gateways.datastore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanDocument, String> {
}
