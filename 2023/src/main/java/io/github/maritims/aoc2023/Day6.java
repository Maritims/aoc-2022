package io.github.maritims.aoc2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Day6 {
    private static final Pattern pattern = Pattern.compile("(\\d+)");

    private final boolean trimWhitespace;

    public Day6(boolean trimWhitespace) {
        this.trimWhitespace = trimWhitespace;
    }

    protected List<Long> getValues(String line) {
        if(trimWhitespace) {
            line = line.replaceAll("\\s", "");
        }
        return pattern.matcher(line)
            .results()
            .map(result -> Long.parseLong(result.group(1)))
            .toList();
    }

    public int solve(String fileName) throws IOException {
        var lines = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        var raceTimes = getValues(lines.get(0));
        var distancesToBeat = getValues(lines.get(1));
        var waysToWin = new int[raceTimes.size()];

        for(int i = 0; i < raceTimes.size(); i++) {
            var raceTime = raceTimes.get(i);
            var distanceToBeat = distancesToBeat.get(i);

            for(int velocity = 0; velocity <= raceTime; velocity++) {
                var remainingTime = raceTime - velocity;
                var travelledDistance = velocity * remainingTime;
                if(travelledDistance > distanceToBeat) {
                    waysToWin[i]++;
                }
            }
        }


        return Arrays.stream(waysToWin).reduce(1, (a, b) -> a * b);
    }
}
