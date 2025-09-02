package io.github.maritims.advent_of_code.common.geometry;

public record Point2D(int col, int row) {
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
}
