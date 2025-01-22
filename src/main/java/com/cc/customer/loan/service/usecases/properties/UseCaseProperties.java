package com.cc.customer.loan.service.usecases.properties;

import java.util.Map;

public record UseCaseProperties(Boolean newFlowEnabled, Map<String, Map<String, String>> feature){}
