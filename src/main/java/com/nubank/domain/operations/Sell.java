package com.nubank.domain.operations;

import com.nubank.domain.SimulationState;
import com.nubank.domain.Operation;
import com.nubank.domain.ResultTax;
import com.nubank.domain.Transaction;

import java.util.function.Supplier;

public class Sell implements Operation {

    @Override
    public Supplier<SimulationState> run(SimulationState state, Transaction transaction) {

        var transactionTotalAmount = transaction.getQuantity() * transaction.getUnitCost();
        var previousTotal = state.getAveragePrice() * transaction.getQuantity();
        Double balance = state.getBalance() + (transactionTotalAmount - previousTotal);
        ResultTax tax = new ResultTax(0.00);

        if (transactionTotalAmount > NO_TAX_LIMIT_VALUE || balance.compareTo(NO_TAX_LIMIT_VALUE) > 0) {
            var totalTax = balance * TAX_RATE;
            if (totalTax > 0) {
                tax = new ResultTax(totalTax);
                balance = 0D;
            }
        }

        state.getResultTaxes().add(tax);
        var newBalance = balance;
        var newQuantity = state.getStockQuantity() - transaction.getQuantity();
        return () -> new SimulationState(
                state.getResultTaxes(),
                newBalance,
                newQuantity,
                state.getAveragePrice()
        );
    }
}
