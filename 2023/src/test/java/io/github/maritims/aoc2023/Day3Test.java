package io.github.maritims.aoc2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.*;

class Day3Test {

    public static Stream<Arguments> getNumbers() {
        var schematic = List.of(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598.."
        );

        return Stream.of(
            arguments(1, schematic, List.of(467, 35, 633, 617, 592, 664, 755, 598)),
            arguments(2, schematic, List.of(16345, 451490))
        );
    }

    public static Stream<Arguments> solve() {
        return Stream.of(
            arguments(1, "day3_sample.txt", 4361),
            arguments(1, "day3_actual.txt", 530495),
            arguments(2, "day3_sample.txt", 467835),
            arguments(2, "day3_actual.txt", 80253814)
        );
    }

    public static Stream<Arguments> getAdjacentNumbers() {
        return Stream.of(
            arguments(null, "467..114..", "...*......", null, List.of()),
            arguments("467..114..", "...*......", "..35..633.", 3, List.of(467, 35)),
            arguments("...*......", "..35..633.", "......#...", 3, List.of()),
            arguments("..35..633.", "......#...", "617*......", 7, List.of(633)),
            arguments("......#...", "617*......", ".....+.58.", 3, List.of(617)),
            arguments("617*......", ".....+.58.", "..592.....", 5, List.of(592)),
            arguments(".....+.58.", "..592.....", "......755.", null, List.of()),
            arguments("..592.....", "......755.", "...$.*....", null, List.of()),
            arguments("......755.", "...$.*....", ".664.598..", 3, List.of(664)),
            arguments("......755.", "...$.*....", ".664.598..", 5, List.of(755, 598))
        );
    }

    @ParameterizedTest
    @MethodSource
    void getNumbers(int requiredAdjacentNumbers, List<String> lines, List<Integer> expectedResult) {
        assertEquals(expectedResult, new Day3(requiredAdjacentNumbers).getPartNumbers(lines));
    }

    @ParameterizedTest
    @MethodSource
    void solve(int requiredAdjacentNumbers, String fileName, int expectedResult) throws IOException {
        assertEquals(expectedResult, new Day3(requiredAdjacentNumbers).solve(fileName));
    }

    @ParameterizedTest
    @MethodSource
    void getAdjacentNumbers(String previousLine, String currentLine, String nextLine, Integer symbolPosition, List<Integer> expectedResult) {
        assertEquals(expectedResult, new Day3(1).getAdjacentNumbers(previousLine, currentLine, nextLine, symbolPosition));
    }
}