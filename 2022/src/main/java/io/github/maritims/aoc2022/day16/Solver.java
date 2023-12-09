package io.github.maritims.aoc2022.day16;

import io.github.maritims.aoc2022.Puzzle;

import java.util.List;

public class Solver extends Puzzle<Integer, Integer> {
    @Override
    public Integer solvePartOne(String filePath) {
        List<Valve> valves = Valve.getFromFile(filePath);
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        List<ValvePath> paths = bfs.getActiveValvePaths(valves);

        paths.forEach(System.out::println);

        return null;
    }

    @Override
    public Integer solvePartTwo(String filePath) {
        return null;
    }
}
