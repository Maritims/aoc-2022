package io.github.maritims.aoc2022;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Puzzle<TPartOneOutput, TPartTwoOutput> {
    protected final List<String> getFileContent(String fileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        return is == null ? Collections.emptyList() : new BufferedReader(new InputStreamReader(is))
                .lines()
                .collect(Collectors.toList());
    }

    public abstract TPartOneOutput solvePartOne(String filePath);

    public abstract TPartTwoOutput solvePartTwo(String filePath);
}
