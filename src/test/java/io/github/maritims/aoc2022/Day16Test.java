package io.github.maritims.aoc2022;

import io.github.maritims.aoc2022.day16.Solver;
import io.github.maritims.aoc2022.day16.Valve;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class Day16Test extends PuzzleTest<Integer, Integer, Solver> {
    public Day16Test() {
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
    public void getValves() {
        List<Valve> valves = Valve.fromFile("16/example.txt");
        System.out.println(valves);
    }

    @Test
    public void getBestValve() {
        List<Valve> valves = Valve.fromFile("16/example.txt");
    }
}
