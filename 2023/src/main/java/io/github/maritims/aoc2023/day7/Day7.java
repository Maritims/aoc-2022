package io.github.maritims.aoc2023.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day7 {
    public int solvePartOne(String fileName) throws IOException {
        var lines      = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        var pattern    = Pattern.compile("^([AKQJT98765432]+) (\\d+)$");
        var rounds     = new ArrayList<Round>();
        var cardValues = new LinkedList<>(List.of('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'));

        for (var line : lines) {
            rounds.addAll(
                pattern.matcher(line)
                    .results()
                    .map(result -> {
                        var hand = new Hand(result.group(1), cardValues);
                        var bid  = Integer.parseInt(result.group(2));
                        return new Round(hand, bid);
                    }).toList());
        }
        rounds.sort(Comparator.comparing(Round::getHand));

        return IntStream.range(0, rounds.size())
            .map(i -> (i + 1) * rounds.get(i).getBid())
            .sum();
    }
}