package io.github.maritims.aoc2015.day2;

import java.util.List;
import java.util.stream.Stream;

public class Present {
    private final int length;
    private final int width;
    private final int height;

    public Present(int length, int width, int height) {
        this.length = length;
        this.width  = width;
        this.height = height;
    }

    protected List<Integer> getSideSurfaceAreas() {
        return List.of(
            (length * width),
            (width * height),
            (height * length)
        );
    }

    protected int getSurfaceArea() {
        return getSideSurfaceAreas().stream()
            .mapToInt(sideSurfaceArea -> sideSurfaceArea)
            .map(sideSurfaceArea -> 2 * sideSurfaceArea)
            .sum();
    }

    protected int getSlack() {
        return getSideSurfaceAreas().stream()
            .min(Integer::compareTo)
            .orElseThrow();
    }

    protected int getSmallestPerimeterOfAnyFace() {
        return Stream.of(
            2 * (length + width),
            2 * (width + height),
            2 * (height + length)
        ).min(Integer::compareTo).orElseThrow();
    }

    protected int getVolume() {
        return length * width * height;
    }

    public int getRequiredWrappingPaperArea() {
        return getSurfaceArea() + getSlack();
    }

    public int getRequiredRibbonLength() {
        return getSmallestPerimeterOfAnyFace() + getVolume();
    }
}
