package io.github.maritims.advent_of_code.common.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collector;

import static io.github.maritims.advent_of_code.common.util.ArgumentExceptionUtil.throwIfNullOrEmpty;

public record LongRange(long start, long end) {
    private static final Pattern PATTERN = Pattern.compile("^(\\d+)-(\\d+)$");

    public LongRange {
        if (start > end) {
            throw new IllegalArgumentException("start must be less than or equal to end");
        }
    }

    public static LongRange fromString(String input) {
        var matcher = PATTERN.matcher(throwIfNullOrEmpty(input, "input"));
        if (!matcher.matches()) {
            throw new IllegalArgumentException("input must be in the format <start>-<end>");
        }

        var start = Long.parseLong(matcher.group(1));
        var end = Long.parseLong(matcher.group(2));
        return new LongRange(start, end);
    }

    public long size() {
        return end - start + 1;
    }

    public boolean contains(long needle) {
        return needle >= start && needle <= end;
    }

    public LongRange merge(LongRange other) {
        if (other == null) {
            throw new IllegalArgumentException("other cannot be null");
        }
        return new LongRange(Math.min(this.start, other.start), Math.max(this.end, other.end));
    }

    public static Collector<LongRange, List<LongRange>, List<LongRange>> mergingOverlaps() {
        return Collector.of(
                ArrayList::new,
                List::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                LongRange::mergeSortedRanges
        );
    }

    private static List<LongRange> mergeSortedRanges(List<LongRange> ranges) {
        if (ranges == null) {
            throw new IllegalArgumentException("ranges cannot be null");
        }
        if (ranges.isEmpty()) {
            return ranges;
        }

        ranges.sort(Comparator.comparing(LongRange::start));

        var mergedRanges = new ArrayList<LongRange>();
        var current = ranges.get(0);

        for (var i = 1; i < ranges.size(); i++) {
            var next = ranges.get(i);

            if (next.start <= current.end + 1) {
                current = current.merge(next);
            } else {
                mergedRanges.add(current);
                current = next;
            }
        }

        mergedRanges.add(current);
        return mergedRanges;
    }
}
