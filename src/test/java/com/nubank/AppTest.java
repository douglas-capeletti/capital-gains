package com.nubank;

import com.nubank.application.CliRunner;
import com.nubank.infrastructure.objectParsers.JsonAdapter;
import com.nubank.infrastructure.userInterfaces.CliAdapter;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest extends TestLoader {

    @TestFactory
    Collection<DynamicTest> dynamicTestsWithCollection() {

        // TIP: to run a specific test, send the case name here, e.g. getInputList("case-all")
        Set<String> inputList = getInputList();

        return inputList.stream()
                .map(inputFile -> DynamicTest.dynamicTest(inputFile, () -> testDefinition(inputFile)))
                .collect(Collectors.toList());
    }

    public void testDefinition(String caseName) {
        // Given
        System.out.println("Case name: " + caseName);
        InputStream input = getInput(caseName);

        // When
        List<String> actualOutput = new CliRunner(new CliAdapter(new JsonAdapter())).run(input);

        // Then
        List<String> expectedOutput = getOutput(caseName);
        assertEquals(expectedOutput, actualOutput);
    }

}