package com.cc.interfaceadapters.gateways.datastore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanDocument, String> {
}
