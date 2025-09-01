package io.github.maritims.advent_of_code.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {
    private final   int     day;
    protected final boolean useSampleData;

    protected Day(int day, boolean useSampleData) {
        this.day           = day;
        this.useSampleData = useSampleData;
    }

    protected final List<String> readAllLines() {
        try {
            return Files.readAllLines(Paths.get("src", "main", "resources", "day" + day + "_" + (useSampleData ? "sample" : "actual") + ".txt"));
        } catch (NoSuchFileException e) {
            throw new RuntimeException("You've forgotten to add puzzle input for day " + day, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected final List<String> readAllLines(String fileName) {
        try {
            return Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        } catch (NoSuchFileException e) {
            throw new RuntimeException("You've forgotten to add puzzle input for day " + day, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract long solvePartOne();

    public abstract long solvePartTwo();
}