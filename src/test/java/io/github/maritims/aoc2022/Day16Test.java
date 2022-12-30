package io.github.maritims.aoc2022;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static io.github.maritims.aoc2022.Day16.Valve;

public class Day16Test extends PuzzleTest<Integer, Integer, Day16> {
    public Day16Test() {
        super(Day16.class);
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
        List<Valve> valves = Day16.getValves("16/example.txt");
        System.out.println(valves);
    }
}
