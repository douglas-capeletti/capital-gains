package com.nubank.infrastructure.userInterfaces;

import com.nubank.domain.ResultTax;
import com.nubank.domain.Simulation;
import com.nubank.domain.Transaction;
import com.nubank.application.ObjectParser;
import com.nubank.application.UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CliAdapter implements UserInterface<InputStream, String> {

    private static final String SIM_FINAL_CHAR = "]";
    private final ObjectParser objectParser;

    public CliAdapter(ObjectParser objectParser) {
        this.objectParser = objectParser;
    }

    public List<Simulation> handleInput(InputStream stdin) {
        List<String> rawSimulations = splitRawSimulations(stdin);
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

    private List<String> splitRawSimulations(InputStream stdin) {
        List<String> simulations = new ArrayList<>();
        StringBuilder simulationLines = new StringBuilder();

        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdin));
        while (true) {
            try {
                line = bufferedReader.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                } else {
                    // End of a simulation
                    if (line.contains(SIM_FINAL_CHAR)) {
                        simulationLines.append(line);
                        // add the current simulation to the list
                        simulations.add(simulationLines.toString());
                        // start a fresh simulation
                        simulationLines = new StringBuilder();
                    } else {
                        simulationLines.append(line);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return simulations;
    }
}
