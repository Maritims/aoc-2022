package io.github.maritims.aoc2022.day16;

import java.util.List;
import java.util.Map;

public class Data {
    private final Map<State, Integer> maximalPressure;
    private final List<ValvePath> paths;

    public Data(Map<State, Integer> maximalPressure, List<ValvePath> paths) {
        this.maximalPressure = maximalPressure;
        this.paths = paths;
    }

    public Map<State, Integer> getMaximalPressure() {
        return maximalPressure;
    }

    public List<ValvePath> getPaths() {
        return paths;
    }
}
