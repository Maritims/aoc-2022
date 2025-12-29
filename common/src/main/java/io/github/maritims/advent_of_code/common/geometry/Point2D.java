package io.github.maritims.advent_of_code.common.geometry;

import java.util.List;

public record Point2D(double col, double row) {
    public static Point2D fromString(String input, String delimiter) {
        if(input == null || input.isBlank()) {
            throw new IllegalArgumentException("input cannot be null or blank");
        }
        if (delimiter == null || delimiter.isBlank()) {
            throw new IllegalArgumentException("delimiter cannot be null or blank");
        }

        var parts = input.split(delimiter);
        return new Point2D(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public static List<Point2D> fromStrings(List<String> input, String delimiter) {
        return input.stream()
                .map(s -> fromString(s, delimiter))
                .toList();
    }

    public static List<Point2D> fromStrings(List<String> input) {
        return fromStrings(input, ",");
    }

    public static Point2D fromString(String input) {
        return fromString(input, ",");
    }

    public Point2D relativeTo(Direction direction, int distance) {
        if(direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }

        var x = switch (direction) {
            case UP, DOWN -> this.col;
            case LEFT -> this.col - distance;
            case RIGHT -> this.col + distance;
        };
        var y = switch (direction) {
            case UP -> this.row + distance;
            case DOWN -> this.row - distance;
            case LEFT, RIGHT -> this.row;
        };
        return new Point2D(x, y);
    }

    public Line2D lineTo(Point2D to) {
        return Line2D.of(this, to);
    }

    public Vector2D vectorTo(Point2D to) {
        return new Vector2D(to.col - col, to.row - row);
    }
}
