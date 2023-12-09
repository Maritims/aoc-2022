package io.github.maritims.aoc2015.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Day6 {
    private static final Pattern pattern = Pattern.compile("^(turn (on|off)|toggle)?\\s?(\\d+,\\d+) through (\\d+,\\d+)$");

    private final boolean isDimmable;

    public Day6(boolean isDimmable) {
        this.isDimmable = isDimmable;
    }

    public int solve(String fileName) throws IOException {
        var lines = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        var lightGrid = new LightGrid(1000, 1000, isDimmable);

        for(var line : lines) {
            var matcher = pattern.matcher(line);

            while(matcher.find()) {
                var matchResult = matcher.toMatchResult();
                var lightInstruction = LightInstruction.fromMatchResult(matchResult);
                lightGrid.executeInstruction(lightInstruction);
            }
        }

        return isDimmable ? lightGrid.getBrightness() : lightGrid.getPoweredLights();
    }
}