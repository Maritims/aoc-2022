package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.util.LongRange;

import java.util.Comparator;
import java.util.HashSet;

public class Day5 extends PuzzleSolver<Long, Long> {
    @Override
    public Long solveFirstPart() {
        var input = loadInput();
        var ranges = new HashSet<LongRange>();
        var fresh = 0L;
        var hasAllRanges = false;

        for (var line : input) {
            if (hasAllRanges) {
                var id = Long.parseLong(line);
                if (ranges.stream().anyMatch(range -> range.contains(id))) {
                    fresh++;
                }
            } else if (line.isBlank()) {
                hasAllRanges = true;
            } else {
                var parts = line.split("-");
                var start = Long.parseLong(parts[0]);
                var end = Long.parseLong(parts[1]);
                ranges.add(new LongRange(start, end));
            }
        }

        return fresh;
    }

    @Override
    public Long solveSecondPart() {
        return loadInput()
                .stream()
                .takeWhile(line -> !line.isBlank()) // No ranges after the first blank line.
                .map(LongRange::fromString)
                .sorted(Comparator.comparingLong(LongRange::start)) // Sort by start value to prepare for merging overlapping ranges.
                .collect(LongRange.mergingOverlaps()) // Reduce ranges by merging overlapping ranges.
                .stream()
                .mapToLong(LongRange::size)
                .sum();
    }
}
