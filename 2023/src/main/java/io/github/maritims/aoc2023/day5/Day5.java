package io.github.maritims.aoc2023.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class Day5 {
    public Long solvePartOne(String fileName) throws IOException {
        var lines = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        lines.removeIf(""::equalsIgnoreCase);

        var seeds = Arrays.stream(lines.remove(0).substring(7).split(" "))
            .map(Long::parseLong)
            .toList();

        var masterMap = new LinkedHashMap<String, List<Range>>();
        String currentMapKey = null;

        for(var line : lines) {
            if (line.endsWith("map:")) {
                currentMapKey = line.split(" ")[0];
                masterMap.put(currentMapKey, new ArrayList<>());
                continue;
            }

            var numbers     = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
            var destination = numbers.get(0);
            var source      = numbers.get(1);
            var length = numbers.get(2);
            var range  = new Range(destination, source, length);

            masterMap.merge(currentMapKey, List.of(range), (a, b) -> {
                a.addAll(b);
                return a;
            });
        }

        Long lowestNumber = null;

        for(var seed : seeds) {
            for(var masterMapEntry : masterMap.entrySet()) {
                for(var range : masterMapEntry.getValue()) {
                    if(seed >= range.getSourceStart() && seed <= range.getSourceEnd()) {
                        seed += range.getDestinationStart() - range.getSourceStart();
                        break;
                    }
                }
            }

            if(lowestNumber == null || lowestNumber > seed) {
                lowestNumber = seed;
            }
        }

        return lowestNumber;
    }
}