package io.github.maritims.advent_of_code.common.geometry;

public record Projection(double min, double max) {
    public boolean overlaps(Projection other) {
        return max >= other.min && other.max >= min;
    }
}
