package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class Day4Test {
    Day4 sut;

    public static Stream<Arguments> walkGrid() {
        return Stream.of(
                Arguments.of("""
                                     ..@@.@@@@.
                                     @@@.@.@.@@
                                     @@@@@.@.@@
                                     @.@@@@..@.
                                     @@.@@@@.@@
                                     .@@@@@@@.@
                                     .@.@.@.@@@
                                     @.@@@.@@@@
                                     .@@@@@@@@.
                                     @.@.@@@.@.
                                     
                                     """, 13),
                Arguments.of("""
                                     ..xx.xx@x.
                                     x@@.@.@.@@
                                     @@@@@.x.@@
                                     @.@@@@..@.
                                     x@.@@@@.@x
                                     .@@@@@@@.@
                                     .@.@.@.@@@
                                     x.@@@.@@@@
                                     .@@@@@@@@.
                                     x.x.@@@.x.""", 12),
                Arguments.of("""
                                     .......x..
                                     .@@.x.x.@x
                                     x@@@@...@@
                                     x.@@@@..x.
                                     .@.@@@@.x.
                                     .x@@@@@@.x
                                     .x.@.@.@@@
                                     ..@@@.@@@@
                                     .x@@@@@@@.
                                     ....@@@...""", 7),
                Arguments.of("""
                                     ..........
                                     .x@.....x.
                                     .@@@@...xx
                                     ..@@@@....
                                     .x.@@@@...
                                     ..@@@@@@..
                                     ...@.@.@@x
                                     ..@@@.@@@@
                                     ..x@@@@@@.
                                     ....@@@...
                                     """, 5),
                Arguments.of("""
                                     ..........
                                     ..x.......
                                     .x@@@.....
                                     ..@@@@....
                                     ...@@@@...
                                     ..x@@@@@..
                                     ...@.@.@@.
                                     ..x@@.@@@x
                                     ...@@@@@@.
                                     ....@@@...""", 2),
                Arguments.of("""
                                     ..........
                                     ..........
                                     ..x@@.....
                                     ..@@@@....
                                     ...@@@@...
                                     ...@@@@@..
                                     ...@.@.@@.
                                     ...@@.@@@.
                                     ...@@@@@x.
                                     ....@@@...""", 1),
                Arguments.of("""
                                     ..........
                                     ..........
                                     ...@@.....
                                     ..x@@@....
                                     ...@@@@...
                                     ...@@@@@..
                                     ...@.@.@@.
                                     ...@@.@@@.
                                     ...@@@@@..
                                     ....@@@...""", 1),
                Arguments.of("""
                                     ..........
                                     ..........
                                     ...x@.....
                                     ...@@@....
                                     ...@@@@...
                                     ...@@@@@..
                                     ...@.@.@@.
                                     ...@@.@@@.
                                     ...@@@@@..
                                     ....@@@...""", 1),
                Arguments.of("""
                                     ..........
                                     ..........
                                     ....x.....
                                     ...@@@....
                                     ...@@@@...
                                     ...@@@@@..
                                     ...@.@.@@.
                                     ...@@.@@@.
                                     ...@@@@@..
                                     ....@@@...""", 1),
                Arguments.of("""
                                     ..........
                                     ..........
                                     ..........
                                     ...x@@....
                                     ...@@@@...
                                     ...@@@@@..
                                     ...@.@.@@.
                                     ...@@.@@@.
                                     ...@@@@@..
                                     ....@@@...""", 0)
        );
    }

    @BeforeEach
    void setUp() {
        sut = spy(new Day4());
    }

    @Test
    void solveFirstPart_withSampleInput() {
        doReturn(Arrays.stream("""
                                       ..@@.@@@@.
                                       @@@.@.@.@@
                                       @@@@@.@.@@
                                       @.@@@@..@.
                                       @@.@@@@.@@
                                       .@@@@@@@.@
                                       .@.@.@.@@@
                                       @.@@@.@@@@
                                       .@@@@@@@@.
                                       @.@.@@@.@.
                                       
                                       """.split("\n")).toList()
        ).when(sut).loadInput();
        assertEquals(13, sut.solveFirstPart());
    }

    @Test
    void solveFirstPart() {
        assertEquals(1493, sut.solveFirstPart());
    }

    @ParameterizedTest
    @MethodSource
    void walkGrid(String input, int expectedResult) {
        var grid   = sut.createGrid(Arrays.stream(input.split("\n")).toList());
        var result = sut.walkGrid(grid, false);
        assertEquals(expectedResult, result);
    }

    @Test
    void solveSecondPart() {
        assertEquals(9194, sut.solveSecondPart());
    }
}