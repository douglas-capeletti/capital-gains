package com.nubank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class TestLoader {

    protected Set<String> getInputList() {
        try {
            var inputPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("input")).getPath();
            try (Stream<Path> stream = Files.list(Path.of(inputPath))) {
                return stream
                        .filter(file -> !Files.isDirectory(file))
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toSet());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected InputStream getInput(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("input/" + path);
    }

    protected List<String> getOutput(String path) {
        String filePath = "output/" + path;
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new RuntimeException("File not found for test execution: " + filePath);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.lines().collect(Collectors.toList());

    }
}
