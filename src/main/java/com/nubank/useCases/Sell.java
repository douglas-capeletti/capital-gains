package com.nubank.useCases;

import com.nubank.application.SimulationState;
import com.nubank.domain.Operation;
import com.nubank.domain.ResultTax;
import com.nubank.domain.Transaction;

public class Sell implements Operation {

    @Override
    public void run(SimulationState state, Transaction transaction) {

        var transactionTotalAmount = transaction.getQuantity() * transaction.getUnitCost();
        var previousTotal = state.getAveragePrice() * transaction.getQuantity();
        Double newBalance = state.getBalance() + (transactionTotalAmount - previousTotal);
        ResultTax tax = new ResultTax(0.00);

        if (transactionTotalAmount > state.NO_TAX_LIMIT_VALUE || newBalance.compareTo(state.NO_TAX_LIMIT_VALUE) > 0) {
            var totalTax = newBalance * state.TAX_RATE;
            if (totalTax > 0) {
                tax = new ResultTax(totalTax);
                newBalance = 0D;
            }
        }

        state.setBalance(newBalance);
        state.getResultTaxes().add(tax);
        state.setStockQuantity(state.getStockQuantity() - transaction.getQuantity());
    }
}
