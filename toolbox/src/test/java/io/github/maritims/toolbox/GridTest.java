package io.github.maritims.toolbox;

import io.github.maritims.toolbox.geometry.Grid;
import io.github.maritims.toolbox.geometry.Point;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GridTest {

    public static Stream<Arguments> parse() {
        return Stream.of(
            arguments(
                List.of(
                    "ABCD",
                    "EFGH",
                    "IJKL"
                ),
                new Character[][] {
                    new Character[] { 'A', 'B', 'C', 'D' },
                    new Character[] { 'E', 'F', 'G', 'H' },
                    new Character[] { 'I', 'J', 'K', 'L' }
                }
            ));
    }

    public static Stream<Arguments> getNeighbours() {
        return Stream.of(
            arguments(
                new Character[][] {
                    new Character[] {'A', 'B', 'C', 'D'},
                    new Character[] {'E', 'F', 'G', 'H'},
                    new Character[] {'I', 'J', 'K', 'L'}
                },
                Point.of(1, 1),
                List.of(
                    Point.of(0, 1), // Directly above.
                    Point.of(1, 0), // Directly to the left.
                    Point.of(1, 2), // Directly to the right.
                    Point.of(2, 1)) // Directly below.
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void parse(List<String> lines, Character[][] expectedResult) {
        var array = new Character[lines.size()][lines.get(0).length()];
        Grid.parse(
            lines,
            (rows, cols) -> new Character[rows][cols],
            (character) -> character,
            (character, points) -> array[points.row()][points.column()] = character
        );
        assertArrayEquals(expectedResult, array);
    }

    @ParameterizedTest
    @MethodSource
    void getNeighbours(Character[][] array, Point source, List<Point> expectedResult) {
        var grid   = new Grid<>(array);
        var result = grid.getNeighbours(source);
        assertEquals(expectedResult, result);
    }
}