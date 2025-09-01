package io.github.maritims.advent_of_code.common.geometry;

public record Point2D(int x, int y) {
    public Point2D relativeTo(Direction direction, int distance) {
        if(direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }

        var x = switch (direction) {
            case UP, DOWN -> this.x;
            case LEFT -> this.x - distance;
            case RIGHT -> this.x + distance;
        };
        var y = switch (direction) {
            case UP -> this.y + distance;
            case DOWN -> this.y - distance;
            case LEFT, RIGHT -> this.y;
        };
        return new Point2D(x, y);
    }
}
