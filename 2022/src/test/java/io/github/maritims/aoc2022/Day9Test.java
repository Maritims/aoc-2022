package io.github.maritims.aoc2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static io.github.maritims.aoc2022.Day9.Move;
import static io.github.maritims.aoc2022.Day9.Move.*;
import static io.github.maritims.aoc2022.Day9.Rope;
import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day9Test extends PuzzleTest<Integer, Integer, Day9> {
    public Day9Test() {
        super(Day9.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return of(
                arguments("ninth/example.txt", 13),
                arguments("ninth/input.txt", 5960)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return of(
                arguments("ninth/example.txt", 1),
                arguments("ninth/input.txt", 2327)
        );
    }

    public Stream<Arguments> moveRope() {
        return of(
                arguments(new Point(4, 0), new Point(3, 0), new Move[] { right(4) }),
                arguments(new Point(0, 4), new Point(0, 3), new Move[] { up(4) }),
                arguments(new Point(2, 2), new Point(1, 2), new Move[] { right(4), up(4), left(3), down(1), right(4), down(1), left(5), right(2) })
        );
    }

    @ParameterizedTest
    @MethodSource
    public void moveRope(Point expectedHead, Point expectedTail, Move... moves) {
        // arrange
        Rope rope = new Rope(2);

        // act
        rope.move(2, moves);

        // assert
        assertEquals(expectedHead, rope.getHead());
        assertEquals(expectedTail, rope.getKnots().get(1));
    }
}
