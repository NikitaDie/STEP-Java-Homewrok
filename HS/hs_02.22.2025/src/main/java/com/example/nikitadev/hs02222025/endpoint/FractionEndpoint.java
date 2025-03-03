package com.example.nikitadev.hs02222025.endpoint;

import com.example.nikitadev.hs02222025.model.rest.Fraction;
import com.example.nikitadev.hs02222025.model.soap.*;
import com.example.nikitadev.hs02222025.service.FractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class FractionEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/nikitadev/hs02222025";

    private final FractionService fractionService;

    @Autowired
    public FractionEndpoint(FractionService fractionService) {
        this.fractionService = fractionService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "isProperRequest")
    @ResponsePayload
    public IsProperResponse isProper(@RequestPayload IsProperRequest request) {
        Fraction fraction = new Fraction(
            request.getFraction().getNumerator(),
            request.getFraction().getDenominator()
        );
        boolean result = fractionService.isProper(fraction);
        IsProperResponse response = new IsProperResponse();
        response.setResult(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "reduceRequest")
    @ResponsePayload
    public ReduceResponse reduce(@RequestPayload ReduceRequest request) {
        Fraction fraction = new Fraction(
            request.getFraction().getNumerator(),
            request.getFraction().getDenominator()
        );
        Fraction reduced = fractionService.reduce(fraction);
        ReduceResponse response = new ReduceResponse();
        response.setFraction(convertToSoapFraction(reduced));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "operationRequest")
    @ResponsePayload
    public OperationResponse performOperation(@RequestPayload OperationRequest request) throws IllegalArgumentException {
        Fraction f1 = new Fraction(
            request.getFraction1().getNumerator(),
            request.getFraction1().getDenominator()
        );
        Fraction f2 = new Fraction(
            request.getFraction2().getNumerator(),
            request.getFraction2().getDenominator()
        );
        Fraction result = switch (request.getOperationType().toLowerCase()) {
            case "add" -> fractionService.add(f1, f2);
            case "subtract" -> fractionService.subtract(f1, f2);
            case "multiply" -> fractionService.multiply(f1, f2);
            case "divide" -> fractionService.divide(f1, f2);
            default ->
                throw new IllegalArgumentException("Invalid operation type: " + request.getOperationType());
        };

        OperationResponse response = new OperationResponse();
        response.setResult(convertToSoapFraction(result));
        return response;
    }

    private SoapFraction convertToSoapFraction(Fraction fraction) {
        SoapFraction soapFraction = new SoapFraction();
        soapFraction.setNumerator(fraction.getNumerator());
        soapFraction.setDenominator(fraction.getDenominator());
        return soapFraction;
    }
}