package io.github.maritims.advent_of_code.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record LongComposition(long[] parts) {
    public LongComposition {
        if (parts == null) {
            throw new IllegalArgumentException("parts cannot be null");
        }
        if (parts.length == 0) {
            throw new IllegalArgumentException("parts cannot be empty");
        }
        parts = parts.clone();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public long toLong() {
        var total = 0L;
        for(var part : parts) {
            if (part < 0) {
                throw new IllegalArgumentException("parts cannot contain negative values");
            }

            var multiplier = 10L;
            while (multiplier <= part) {
                multiplier *= 10;
            }

            total = (total * multiplier) + part;
        }
        return total;
    }

    @Override
    public String toString() {
        return "LongComposition{" +
                "parts=" + Arrays.toString(parts) +
                '}';
    }

    public static class Builder {
        private final List<Long> parts = new ArrayList<>();

        public Builder addPart(long part) {
            if (part < 0) {
                throw new IllegalArgumentException("part cannot be negative");
            }
            this.parts.add(part);
            return this;
        }

        public LongComposition build() {
            return new LongComposition(parts.stream().mapToLong(Long::longValue).toArray());
        }
    }
}
