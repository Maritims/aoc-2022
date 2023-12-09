package io.github.maritims.aoc2015.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day2 {
    private static final Pattern dimensionPattern = Pattern.compile("^(\\d+)x(\\d+)x(\\d+)");

    protected Present getPresent(MatchResult matchResult) {
        int length = Integer.parseInt(matchResult.group(1));
        int width = Integer.parseInt(matchResult.group(2));
        int height = Integer.parseInt(matchResult.group(3));

        return new Present(length, width, height);
    }

    protected int getRequiredWrappingPaperArea(String line) {
        return dimensionPattern.matcher(line)
            .results()
            .map(this::getPresent)
            .map(Present::getRequiredWrappingPaperArea)
            .mapToInt(i -> i)
            .sum();
    }

    protected int getRequiredRibbonLength(String line) {
        return dimensionPattern.matcher(line)
            .results()
            .map(this::getPresent)
            .map(Present::getRequiredRibbonLength)
            .mapToInt(i -> i)
            .sum();
    }

    public int solvePartOne(String fileName) throws IOException {
        return Files.readAllLines(Paths.get("src", "main", "resources", fileName))
            .stream()
            .mapToInt(this::getRequiredWrappingPaperArea)
            .sum();
    }

    public int solvePartTwo(String fileName) throws IOException {
        return Files.readAllLines(Paths.get("src", "main", "resources", fileName))
            .stream()
            .map(this::getRequiredRibbonLength)
            .mapToInt(i -> i)
            .sum();
    }
}
