package io.github.maritims.aoc2023.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;

public class Day7 {
    public int solve(String fileName) throws IOException {
        var lines = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        var pattern = Pattern.compile("^([AKQJT98765432]+) (\\d+)$");
        var rounds = new ArrayList<Round>();

        for(var line : lines) {
            rounds.addAll(
                pattern.matcher(line)
                    .results()
                    .map(result -> {
                        var hand = new Hand(result.group(1));
                        var bid  = Integer.parseInt(result.group(2));
                        return new Round(hand, bid);
                    }).toList());
        }

        rounds.sort(Comparator.comparing(o -> o.getHand().getHandType().ordinal()));

        System.out.println(rounds);

        return 0;
    }
}
