package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

class Day5Test extends PuzzleTest<String, String, Day5> {

    public Day5Test() {
        super(Day5.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(Arguments.arguments("fifth/example.txt", "CMZ"), Arguments.arguments("fifth/input.txt", "TLNGFGMFN"));
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(Arguments.arguments("fifth/example.txt", "MCD"), Arguments.arguments("fifth/input.txt", "FGLQJCMBD"));
    }
}