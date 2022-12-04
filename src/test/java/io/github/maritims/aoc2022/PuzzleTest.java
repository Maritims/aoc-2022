package io.github.maritims.aoc2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    public static Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments(1, FirstPuzzle.class, "first/example.txt", 24000),
                Arguments.arguments(1, FirstPuzzle.class, "first/input.txt", 67016),
                Arguments.arguments(2, SecondPuzzle.class, "second/example.txt", 15),
                Arguments.arguments(2, SecondPuzzle.class, "second/input.txt", 13565),
                Arguments.arguments(3, ThirdPuzzle.class, "third/example.txt", 157),
                Arguments.arguments(3, ThirdPuzzle.class, "third/input.txt", 7746),
                Arguments.arguments(4, FourthPuzzle.class, "fourth/example.txt", 2),
                Arguments.arguments(4, FourthPuzzle.class, "fourth/input.txt", 580)
        );
    }

    public static Stream<Arguments> solvePartTwo() {
        return Stream.of(
                Arguments.arguments(1, FirstPuzzle.class, "first/example.txt", 45000),
                Arguments.arguments(1, FirstPuzzle.class, "first/input.txt", 200116),
                Arguments.arguments(2, SecondPuzzle.class, "second/example.txt", 12),
                Arguments.arguments(2, SecondPuzzle.class, "second/input.txt", 12424),
                Arguments.arguments(3, ThirdPuzzle.class, "third/example.txt", 70),
                Arguments.arguments(3, ThirdPuzzle.class, "third/input.txt", 2604)
        );
    }

    @ParameterizedTest(name = "Day {0}, part 1, {2}")
    @MethodSource
    void solvePartOne(int day, Class<Puzzle> puzzleClass, String filePath, int expectedResult) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        assertEquals(expectedResult, puzzleClass.getConstructor(String.class).newInstance(filePath).solvePartOne());
    }

    @ParameterizedTest(name = "Day {0}, part 2, {2}")
    @MethodSource
    void solvePartTwo(int day, Class<Puzzle> puzzleClass, String filePath, int expectedResult) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        assertEquals(expectedResult, puzzleClass.getConstructor(String.class).newInstance(filePath).solvePartTwo());
    }
}
