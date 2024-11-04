package com.nubank.adapters;

import com.nubank.domain.ResultTax;
import com.nubank.domain.Simulation;
import com.nubank.domain.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CliAdapter {

    public ObjectParser objectParser;

    public CliAdapter(ObjectParser objectParser) {
        this.objectParser = objectParser;
    }

    public List<Simulation> handleInput(InputStream stdin) throws IOException {
        List<String> rawSimulations = splitSimulations(stdin);
        List<Simulation> simulations = new ArrayList<>();

        for (String simulation : rawSimulations) {
            List<Transaction> simTransactions = objectParser.stringToObjectList(simulation, Transaction.class);
            simulations.add(new Simulation(simTransactions));
        }
        return simulations;
    }

    public String handleOutput(List<ResultTax> resultTaxes) {
        return objectParser.objectListToString(resultTaxes);
    }

    private List<String> splitSimulations(InputStream stdin) throws IOException {
        List<String> simulations = new ArrayList<>();
        StringBuilder simulationLines = new StringBuilder();

        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdin));
        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            // End of a simulation
            if (line.contains("]")) {
                simulationLines.append(line);
                // add the current simulation to the list
                simulations.add(simulationLines.toString());
                // start a fresh simulation
                simulationLines = new StringBuilder();
            } else {
                simulationLines.append(line);
            }
        }
        return simulations;
    }
}
