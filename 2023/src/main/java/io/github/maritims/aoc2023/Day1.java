package io.github.maritims.aoc2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    private final boolean supportDigitWords;
    private final boolean verbose;
    private final Pattern pattern;

    public Day1(boolean supportDigitWords, boolean verbose) {
        this.supportDigitWords = supportDigitWords;
        this.verbose           = verbose;

        if (supportDigitWords) {
            // The regex engine will always consume a character upon a match.
            // Use positive lookahead to circumvent consumption of characters and support overlapping matches in strings such as "three28jxdmlqfmc619lightweight" where "eight" and "two" share the letter "t".
            pattern = Pattern.compile("(?=(\\d|one|two|three|four|five|six|seven|eight|nine))");
        } else {
            pattern = Pattern.compile("\\d");
        }
    }

    protected int convertLineToNumber(String line) {
        Stream<String> streamOfDigits = pattern.matcher(line)
            .results()
            .map(matchResult -> supportDigitWords ? matchResult.group(1) : matchResult.group(0));

        if (supportDigitWords) {
            streamOfDigits = streamOfDigits.map(group -> group.replace("one", "1")
                .replace("two", "2")
                .replace("three", "3")
                .replace("four", "4")
                .replace("five", "5")
                .replace("six", "6")
                .replace("seven", "7")
                .replace("eight", "8")
                .replace("nine", "9")
            );
        }

        List<String> digits = streamOfDigits.collect(Collectors.toList());
        int          sum    = Integer.parseInt(digits.get(0) + digits.get(digits.size() - 1));

        if (verbose) {
            System.out.println(line + " -> " + sum);
        }

        return sum;
    }

    public int solve(String fileName) throws IOException {
        return Files.readAllLines(Paths.get("src", "main", "resources", fileName))
            .stream()
            .mapToInt(this::convertLineToNumber)
            .sum();
    }
}