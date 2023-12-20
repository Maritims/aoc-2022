package io.github.maritims.aoc2023.day11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class Day11Test {

    public static Stream<Arguments> getExpandedGrid() {
        return Stream.of(
            arguments(true, """
                    ....#........
                    .........#...
                    #............
                    .............
                    .............
                    ........#....
                    .#...........
                    ............#
                    .............
                    .............
                    .........#...
                    #....#.......
                    """)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getExpandedGrid(boolean useSampleData, String expectedGrid) {
        var grid = new Day11(useSampleData).getExpandedGrid();
        assertEquals(expectedGrid, grid.toString());
    }
}