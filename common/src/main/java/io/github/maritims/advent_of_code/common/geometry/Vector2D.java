package io.github.maritims.advent_of_code.common.geometry;

public record Vector2D(double x, double y) {
    public Vector2D(Point2D start, Point2D end) {
        this(end.col() - start.col(), end.row() - start.row());
    }

    Vector2D perpendicular() {
        return new Vector2D(-y, x);
    }

    Vector2D normalize() {
        var magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return new Vector2D(x / magnitude, y / magnitude);
    }

    double cross(Vector2D other) {
        return x * other.y - y * other.x;
    }
}
