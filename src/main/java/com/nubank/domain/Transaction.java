package com.nubank.domain;

import com.nubank.domain.operations.OperationFactory;

public class Transaction {

    private final String operationName;
    private final Operation operation;
    private final Double unitCost;
    private final Long quantity;

    public Transaction(String operationName, Double unitCost, Long quantity) {
        this.operationName = operationName;
        this.operation = OperationFactory.build(operationName);
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    public String getOperationName() {
        return operationName;
    }

    public Operation getOperation() {
        return operation;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public Long getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "operation=" + operationName +
                ", unitCost=" + unitCost +
                ", quantity=" + quantity +
                '}';
    }
}
