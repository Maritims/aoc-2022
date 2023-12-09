package io.github.maritims.aoc2022.day16;

import io.github.maritims.aoc2022.PuzzleTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class SolverTest extends PuzzleTest<Integer, Integer, Solver> {
    public SolverTest() {
        super(Solver.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("16/example.txt", 1651)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return null;
    }

    @Test
    public void getBestValve() {
        List<Valve> valves = Valve.getFromFile("16/example.txt");
    }
}
