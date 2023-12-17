package io.github.maritims.aoc2023.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day7 {
    private static final Pattern pattern = Pattern.compile("^([AKQJT98765432]+) (\\d+)$");

    protected List<Round> parse(List<String> lines, Function<String, Hand> handFactory) {
        var rounds = new ArrayList<Round>();
        for (var line : lines) {
            rounds.addAll(
                pattern.matcher(line)
                    .results()
                    .map(result -> {
                        var hand = handFactory.apply(result.group(1));
                        var bid  = Integer.parseInt(result.group(2));
                        return new Round(hand, bid);
                    }).toList());
        }
        return rounds;
    }

    public int solvePartOne(String fileName) throws IOException {
        var lines      = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        var rounds     = parse(lines, line -> new Hand(line, false));
        rounds.sort(Comparator.comparing(Round::getHand));

        return IntStream.range(0, rounds.size())
            .map(i -> (i + 1) * rounds.get(i).getBid())
            .sum();
    }

    public int solvePartTwo(String fileName) throws IOException {
        var lines      = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        var rounds     = parse(lines, line -> new Hand(line, true));
        rounds.sort(Comparator.comparing(Round::getHand));

        return IntStream.range(0, rounds.size())
            .map(i -> (i + 1) * rounds.get(i).getBid())
            .sum();
    }
}