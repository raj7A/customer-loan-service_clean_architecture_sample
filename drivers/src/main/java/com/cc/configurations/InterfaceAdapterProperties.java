package com.cc.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "application.interfaceadapters")
public class InterfaceAdapterProperties {
    private Map<String, Map<String, String>> feature;

    public Map<String, Map<String, String>> getFeature() {
        return feature;
    }

    public void setFeature(Map<String, Map<String, String>> feature) {
        this.feature = feature;
    }
}
