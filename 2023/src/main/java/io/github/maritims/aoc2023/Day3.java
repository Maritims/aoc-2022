package io.github.maritims.aoc2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    private static final Pattern numberPattern     = Pattern.compile("(\\d+)");
    private static final Pattern enginePartPattern = Pattern.compile("[^\\d.]");
    private static final Pattern gearPattern       = Pattern.compile("(\\*)");

    private final int requiredAdjacentPartNumbers;

    public Day3(int requiredAdjacentPartNumbers) {
        this.requiredAdjacentPartNumbers = requiredAdjacentPartNumbers;
    }

    protected List<Integer> getAdjacentNumbers(Matcher matcher, Integer symbolPosition) {
        var adjacentNumbers = new ArrayList<Integer>();

        while (matcher.find()) {
            var matchResult = matcher.toMatchResult();
            if (symbolPosition >= matchResult.start() - 1 && symbolPosition <= matchResult.end()) {
                adjacentNumbers.add(Integer.parseInt(matcher.group(1)));
            }
        }

        return adjacentNumbers;
    }

    protected List<Integer> getAdjacentNumbers(String previousLine, String currentLine, String nextLine, Integer symbolPosition) {
        var adjacentNumbers = new ArrayList<Integer>();
        if (symbolPosition == null) {
            return adjacentNumbers;
        }

        Optional.ofNullable(previousLine)
            .map(line -> getAdjacentNumbers(numberPattern.matcher(line), symbolPosition))
            .ifPresent(adjacentNumbers::addAll);
        Optional.ofNullable(nextLine)
            .map(line -> getAdjacentNumbers(numberPattern.matcher(line), symbolPosition))
            .ifPresent(adjacentNumbers::addAll);

        var currentLineMatcher = numberPattern.matcher(currentLine);
        while (currentLineMatcher.find()) {
            var matchResult = currentLineMatcher.toMatchResult();
            if (symbolPosition == matchResult.start() - 1 || symbolPosition == matchResult.end()) {
                adjacentNumbers.add(Integer.parseInt(currentLineMatcher.group(1)));
            }
        }

        return adjacentNumbers;
    }

    protected List<Integer> getPartNumbers(List<String> lines) {
        var listIterator = lines.listIterator();
        var partNumbers      = new ArrayList<Integer>();

        while (listIterator.hasNext()) {
            String previousLine = null;
            if (listIterator.hasPrevious()) {
                previousLine = listIterator.previous();
                listIterator.next();
            }
            var    currentLine = listIterator.next();
            String nextLine    = null;
            if (listIterator.hasNext()) {
                nextLine = listIterator.next();
                listIterator.previous();
            }

            Pattern symbolPattern;
            if(requiredAdjacentPartNumbers == 1) {
                symbolPattern = enginePartPattern;
            } else if(requiredAdjacentPartNumbers == 2) {
                symbolPattern = gearPattern;
            } else {
                throw new IllegalStateException("Unable to determine pattern from number of required adjacent numbers");
            }

            var matcher = symbolPattern.matcher(currentLine);
            while (matcher.find()) {
                var matchResult     = matcher.toMatchResult();
                var start           = matchResult.start();
                var adjacentPartNumbers = getAdjacentNumbers(previousLine, currentLine, nextLine, start);

                if (adjacentPartNumbers.size() >= requiredAdjacentPartNumbers) {
                    if(requiredAdjacentPartNumbers == 1) {
                        partNumbers.addAll(adjacentPartNumbers);
                    } else {
                        partNumbers.add(adjacentPartNumbers.stream().reduce(1, (a , b) -> a * b));
                    }
                }
            }
        }

        return partNumbers;
    }

    public int solve(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        return getPartNumbers(lines)
            .stream()
            .mapToInt(i -> i)
            .sum();
    }
}