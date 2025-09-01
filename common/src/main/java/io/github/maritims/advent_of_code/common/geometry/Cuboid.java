package io.github.maritims.advent_of_code.common.geometry;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class Cuboid {
    private static final Pattern CUBOID_DIMENSION_PATTERN = Pattern.compile("^(\\d+)x(\\d+)x(\\d+)");

    private final int length;
    private final int width;
    private final int height;

    public Cuboid(int length, int width, int height) {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Cuboid dimensions must be greater than 0");
        }

        this.length = length;
        this.width  = width;
        this.height = height;
    }

    public static Cuboid fromString(String input) {
        var matcher = CUBOID_DIMENSION_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Input must be in the format AxBxC where A, B and C are positive integers");
        }

        var length = Integer.parseInt(matcher.group(1));
        var width  = Integer.parseInt(matcher.group(2));
        var height = Integer.parseInt(matcher.group(3));

        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Cuboid dimensions must be greater than 0");
        }

        return new Cuboid(length, width, height);
    }

    public int getVolume() {
        return length * width * height;
    }

    public List<Face> getFaces() {
        return List.of(
                new Face(width, length),
                new Face(length, height),
                new Face(width, height)
        );
    }

    public Face getSmallestFaceByPerimeter() {
        return getFaces()
                .stream()
                .min(Comparator.comparingInt(Face::getPerimeter))
                .orElseThrow();
    }

    public Face getSmallestFaceByArea() {
        return getFaces()
                .stream()
                .min(Comparator.comparingInt(Face::getArea))
                .orElseThrow();
    }

    public int getSurfaceArea() {
        return getFaces()
                .stream()
                .mapToInt(face -> 2 * face.getArea()) // Each face appears twice.
                .sum();
    }
}
