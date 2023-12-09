package io.github.maritims.aoc2023.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5 {
    protected LinkedHashMap<String, List<Range>> getMasterMap(List<String> lines) {
        var    masterMap     = new LinkedHashMap<String, List<Range>>();
        String currentMapKey = null;

        for (var line : lines) {
            if (line.endsWith("map:")) {
                currentMapKey = line.split(" ")[0];
                masterMap.put(currentMapKey, new ArrayList<>());
                continue;
            }

            var numbers     = Arrays.stream(line.split(" "))
                .map(Long::parseLong)
                .map(Long::doubleValue)
                .toList();
            var destination = numbers.get(0);
            var source      = numbers.get(1);
            var length      = numbers.get(2);
            var range       = new Range(destination, source, length);

            masterMap.merge(currentMapKey, List.of(range), (a, b) -> {
                a.addAll(b);
                a = a.stream()
                    .sorted(Comparator.comparing(Range::getSourceStart))
                    .collect(Collectors.toCollection(ArrayList::new));
                return a;
            });
        }

        return masterMap;
    }

    public Double solvePartOne(String fileName) throws IOException {
        var lines = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        lines.removeIf(""::equalsIgnoreCase);

        var seeds = Arrays.stream(lines.remove(0).substring(7).split(" "))
            .map(Long::parseLong)
            .map(Long::doubleValue)
            .toList();
        var masterMap    = getMasterMap(lines);
        var lowestNumber = (Double) null;

        for (var seed : seeds) {
            for (var masterMapEntry : masterMap.entrySet()) {
                for (var range : masterMapEntry.getValue()) {
                    if (seed >= range.getSourceStart() && seed <= range.getSourceEnd()) {
                        seed += range.getDestinationStart() - range.getSourceStart();
                        break;
                    }
                }
            }

            if (lowestNumber == null || lowestNumber > seed) {
                lowestNumber = seed;
            }
        }

        return lowestNumber;
    }

    public Double solvePartTwo(String fileName) throws IOException {
        var lines = Files.readAllLines(Paths.get("src", "main", "resources", fileName));
        lines.removeIf(""::equalsIgnoreCase);

        var seedRanges = Pattern.compile("(\\d+ \\d+)")
            .matcher(lines.remove(0))
            .results()
            .map(result -> {
                var numbers = Arrays.stream(result.group(1).split(" "))
                    .map(Long::parseLong)
                    .map(Long::doubleValue)
                    .toList();
                var sourceStart = numbers.get(0);
                var length      = numbers.get(1);
                return new Range(null, sourceStart, length);
            })
            .toList();
        var masterMap = getMasterMap(lines);
        var piecewiseFunctions = new ArrayList<PiecewiseFunction>();

        for(var entry : masterMap.entrySet()) {
            var pieces = new ArrayList<Piece>();
            for(var i = 0; i < entry.getValue().size(); i++) {
                var range = entry.getValue().get(i);

                if(i == 0) {
                    pieces.add(new Piece(Double.NEGATIVE_INFINITY, range.getSourceStart() - 1.0, 0.0));
                }

                pieces.add(new Piece(range.getSourceStart(), range.getSourceEnd(), range.getOffset()));

                if(i == entry.getValue().size() - 1) {
                    pieces.add(new Piece(range.getSourceStart() + range.getLength(), Double.POSITIVE_INFINITY, 0));
                }
            }

            piecewiseFunctions.add(new PiecewiseFunction(pieces));
        }

        var lowestNumber = (Double) null;

        for(var seedRange : seedRanges) {
            for(var seed = seedRange.getSourceStart(); seed <= seedRange.getSourceEnd(); seed++) {
                var currentNumber = seed;

                for(var piecewiseFunction : piecewiseFunctions) {
                    currentNumber = piecewiseFunction.evaluate(currentNumber);
                }

                if(lowestNumber == null || lowestNumber > currentNumber) {
                    lowestNumber = currentNumber;
                }
            }
        }

        return lowestNumber;
    }
}