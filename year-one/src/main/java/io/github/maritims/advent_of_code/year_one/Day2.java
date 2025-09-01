package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.geometry.Cuboid;

public class Day2 extends PuzzleSolver<Integer, Integer> {
    public Integer solveFirstPart() {
        return loadInput()
                .stream()
                .map(Cuboid::fromString)
                .mapToInt(cuboid -> cuboid.getSurfaceArea() + cuboid.getSmallestFaceByArea().getArea())
                .sum();
    }

    public Integer solveSecondPart() {
        return loadInput()
                .stream()
                .map(Cuboid::fromString)
                .mapToInt(cuboid -> cuboid.getVolume() + cuboid.getSmallestFaceByPerimeter().getPerimeter())
                .sum();
    }
}
