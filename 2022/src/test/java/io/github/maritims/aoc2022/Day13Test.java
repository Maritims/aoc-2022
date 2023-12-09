package io.github.maritims.aoc2022;

import io.github.maritims.lib.Tuple2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day13Test extends PuzzleTest<Integer, Integer, Day13> {
    public Day13Test() {
        super(Day13.class);
    }

    public static Stream<Arguments> parse() {
        return Stream.of(
                arguments("[1,1,3,1,1]", asList(1, 1, 3, 1, 1)),
                arguments("[1,1,5,1,1]", asList(1, 1, 5, 1, 1)),
                arguments("[[1],[2,3,4]]", asList(singletonList(1), asList(2, 3, 4)))
        );
    }

    @ParameterizedTest
    @MethodSource
    public void parse(String line, List<Integer> expectedResult) {
        Day13 sut = new Day13();
        Tuple2<List<Object>, Integer> tuple = sut.parse(line, 1);
        assertEquals(expectedResult, tuple.getItem1());
    }

    public Stream<Arguments> compare() {
        return Stream.of(
                arguments(asList(1, 1, 3, 1, 1), asList(1, 1, 5, 1, 1), -1),
                arguments(asList(singletonList(1), asList(2, 3, 4)), asList(singletonList(1), 4), -1),
                arguments(singletonList(9), singletonList(asList(8, 7, 6)), 1),
                arguments(asList(asList(4, 4), 4, 4), asList(asList(4, 4), 4, 4, 4), -1),
                arguments(asList(7, 7, 7, 7), asList(7, 7, 7), 1),
                arguments(emptyList(), singletonList(3), -1),
                arguments(singletonList(singletonList(emptyList())), singletonList(emptyList()), 1),
                arguments(asList(1, asList(2, asList(3, asList(4, asList(5, 6, 7)))), 8, 9), asList(1, asList(2, asList(3, asList(4, asList(5, 6, 0)))), 8, 9), 1)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void compare(List<Object> left, List<Object> right, int expectedResult) {
        // arrange
        Day13 sut = new Day13();
        sut.isLogEnabled = true;
        assertEquals(expectedResult, sut.COMPARATOR.compare(left, right));
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                arguments("13/example.txt", 13),
                arguments("13/input.txt", 5623)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(
                arguments("13/example.txt", 140),
                arguments("13/input.txt", 20570)
        );
    }
}