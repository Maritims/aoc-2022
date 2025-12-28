package io.github.maritims.advent_of_code.common.geometry;

import java.util.List;
import java.util.Objects;

public record Point3D(double x, double y, double z) {
    public static Point3D fromString(String input) {
        var parts = input.split(",");
        return new Point3D(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    public static List<Point3D> fromStrings(List<String> inputs) {
        return inputs.stream()
                .map(Point3D::fromString)
                .toList();
    }

    public double getCoordinate(Axis axis) {
        Objects.requireNonNull(axis, "axis cannot be null");

        return switch (axis) {
            case X -> x;
            case Y -> y;
            case Z -> z;
        };
    }

    public double getEuclideanDistance(Point3D other) {
        var dx = x - other.x;
        var dy = y - other.y;
        var dz = z - other.z;

        var dx2 = Math.pow(dx, 2);
        var dy2 = Math.pow(dy, 2);
        var dz2 = Math.pow(dz, 2);

        return Math.sqrt(dx2 + dy2 + dz2);
    }

    public double getSquaredDistance(Point3D other) {
        var dx = x - other.x;
        var dy = y - other.y;
        var dz = z - other.z;

        var dx2 = Math.pow(dx, 2);
        var dy2 = Math.pow(dy, 2);
        var dz2 = Math.pow(dz, 2);

        return dx2 + dy2 + dz2;
    }
}
