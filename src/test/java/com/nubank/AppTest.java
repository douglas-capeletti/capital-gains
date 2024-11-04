package com.nubank;

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

        Set<String> inputList = getInputList();

        return inputList.stream()
                .map(inputFile -> DynamicTest.dynamicTest(inputFile, () -> testDefinition(inputFile)))
                .collect(Collectors.toList());
    }

    public void testDefinition(String caseName) throws IOException {
        // Given
        InputStream input = getInput(caseName);

        // When
        List<String> actualOutput = App.run(input);

        // Then
        List<String> expectedOutput = getOutput(caseName);
        assertEquals(expectedOutput, actualOutput);
    }

}