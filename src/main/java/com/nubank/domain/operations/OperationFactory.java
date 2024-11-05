package com.nubank.domain.operations;

import com.nubank.domain.Operation;

public class OperationFactory {

    public static Operation build(String operationName) throws RuntimeException {
        switch (operationName) {
            case "buy":
                return new Buy();
            case "sell":
                return new Sell();
            default:
                throw new RuntimeException("Unknown operation");
        }
    }

}
