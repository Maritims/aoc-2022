package io.github.maritims.aoc2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class Day5 {
    protected boolean isNiceInPartOne(String line) {
        Pattern vowelPattern = Pattern.compile("([aeiou])");
        Pattern doubleLetterPattern = Pattern.compile("(.)\\1");
        Pattern forbiddenPattern = Pattern.compile("(ab|cd|pq|xy)");

        return vowelPattern.matcher(line).results().count() >= 3 && doubleLetterPattern.matcher(line).find() && !forbiddenPattern.matcher(line).find();
    }

    protected boolean isNiceInPartTwo(String line) {
        Pattern repeatingPairPattern = Pattern.compile("(\\w{2}).*?(\\1)");
        Pattern repeatingLetterPattern = Pattern.compile("(\\w).(\\1)");

        return repeatingPairPattern.matcher(line).find() && repeatingLetterPattern.matcher(line).find();
    }

    public int solvePartOne(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        return (int) lines.stream()
            .filter(this::isNiceInPartOne)
            .count();
    }

    public int solvePartTwo(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        return (int) lines.stream()
            .filter(this::isNiceInPartTwo)
            .count();
    }
}
