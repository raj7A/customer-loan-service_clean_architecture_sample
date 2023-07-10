package com.cc.configurations;

import com.cc.usecases.properties.UseCaseProperties;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-10T13:34:53+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2.1 (Homebrew)"
)
public class UseCaseConfigs$ConfigMapperImpl implements UseCaseConfigs.ConfigMapper {

    @Override
    public UseCaseProperties toDomainConfig(com.cc.configurations.UseCaseProperties useCaseProperties) {
        if ( useCaseProperties == null ) {
            return null;
        }

        Map<String, Map<String, String>> feature = null;

        Map<String, Map<String, String>> map = useCaseProperties.getFeature();
        if ( map != null ) {
            feature = new LinkedHashMap<String, Map<String, String>>( map );
        }

        UseCaseProperties useCaseProperties1 = new UseCaseProperties( feature );

        return useCaseProperties1;
    }
}
