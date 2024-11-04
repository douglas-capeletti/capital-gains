package com.nubank.application;

import com.nubank.domain.ResultTax;
import com.nubank.domain.Simulation;
import com.nubank.domain.Transaction;

import java.util.List;

public class Simulator {
    public List<ResultTax> handle(Simulation simulation) {
        SimulationState state = new SimulationState();
        for (Transaction transaction : simulation.getOperations()) {
            transaction.getOperation().run(state, transaction);
        }
        return state.getResultTaxes();
    }
}

