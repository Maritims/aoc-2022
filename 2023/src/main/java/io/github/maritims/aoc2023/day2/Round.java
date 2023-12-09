package io.github.maritims.aoc2023.day2;

import java.util.Map;

public class Round {
    private final Map<Color, Integer> cubes;

    public Round(Map<Color, Integer> cubes) {
        this.cubes = cubes;
    }

    public Map<Color, Integer> getCubes() {
        return cubes;
    }
}
