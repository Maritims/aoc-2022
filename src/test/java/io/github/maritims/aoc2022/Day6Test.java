package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Day6Test extends PuzzleTest<Integer, Integer, Day6> {
    public Day6Test() {
        super(Day6.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("sixth/example1.txt", 7),
                Arguments.arguments("sixth/example2.txt", 5),
                Arguments.arguments("sixth/example3.txt", 6),
                Arguments.arguments("sixth/example4.txt", 10),
                Arguments.arguments("sixth/example5.txt", 11),
                Arguments.arguments("sixth/input.txt", 1855)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(
                Arguments.arguments("sixth/example1.txt", 19),
                Arguments.arguments("sixth/example2.txt", 23),
                Arguments.arguments("sixth/example3.txt", 23),
                Arguments.arguments("sixth/example4.txt", 29),
                Arguments.arguments("sixth/example5.txt", 26),
                Arguments.arguments("sixth/input.txt", 3256)
        );
    }
}
