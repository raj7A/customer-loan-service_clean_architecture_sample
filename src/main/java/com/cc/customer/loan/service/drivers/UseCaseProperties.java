package com.cc.customer.loan.service.drivers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "application.usecase")
public class UseCaseProperties {
    private Map<String, Map<String, String>> feature;

    public Map<String, Map<String, String>> getFeature() {
        return feature;
    }

    public void setFeature(Map<String, Map<String, String>> feature) {
        this.feature = feature;
    }
}