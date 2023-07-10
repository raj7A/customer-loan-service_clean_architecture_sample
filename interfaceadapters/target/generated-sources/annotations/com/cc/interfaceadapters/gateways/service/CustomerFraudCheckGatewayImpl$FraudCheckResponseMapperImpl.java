package com.cc.interfaceadapters.gateways.service;

import com.cc.usecases.createloanusecase.FraudCheckResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-10T13:34:51+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2.1 (Homebrew)"
)
public class CustomerFraudCheckGatewayImpl$FraudCheckResponseMapperImpl implements CustomerFraudCheckGatewayImpl.FraudCheckResponseMapper {

    @Override
    public FraudCheckResponse fraudCheckServiceResponseToFraudCheckResponse(FraudCheckServiceResponse fraudCheckServiceResponse) {
        if ( fraudCheckServiceResponse == null ) {
            return null;
        }

        String customerId = null;
        Boolean isFraud = null;

        customerId = fraudCheckServiceResponse.customerId();
        isFraud = fraudCheckServiceResponse.isFraud();

        FraudCheckResponse fraudCheckResponse = new FraudCheckResponse( customerId, isFraud );

        return fraudCheckResponse;
    }
}
