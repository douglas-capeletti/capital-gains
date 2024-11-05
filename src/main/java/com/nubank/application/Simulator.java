package com.nubank.application;

import com.nubank.App;
import com.nubank.domain.ResultTax;
import com.nubank.domain.Simulation;
import com.nubank.domain.SimulationState;
import com.nubank.domain.Transaction;

import java.util.List;
import java.util.function.Supplier;

public class Simulator {
    public List<ResultTax> handle(Simulation simulation) {
        if(App.debug()){
            System.out.println("____________________________________________________________________________________________________");
            System.out.println();
        }
        SimulationContext ctx = new SimulationContext();
        for (Transaction transaction : simulation.getOperations()) {

            // Keeping the state object immutable
            Supplier<SimulationState> stateUpdater = transaction.getOperation().run(ctx.getState(), transaction);
            ctx.setState(stateUpdater.get());

            if(App.debug()){
                System.out.println(transaction);
                System.out.println(ctx.getState());
                System.out.println();
            }
        }
        return ctx.getState().getResultTaxes();
    }
}

