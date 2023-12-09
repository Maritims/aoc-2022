package io.github.maritims.aoc2022;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Day10Test extends PuzzleTest<Integer, String, Day10> {
    public Day10Test() {
        super(Day10.class);
    }

    @Override
    public Stream<Arguments> solvePartOne() {
        return Stream.of(
                Arguments.arguments("tenth/example.txt", 13140),
                Arguments.arguments("tenth/input.txt", 15880)
        );
    }

    @Override
    public Stream<Arguments> solvePartTwo() {
        return Stream.of(
                Arguments.arguments(
                        "tenth/example.txt",
                        "##..##..##..##..##..##..##..##..##..##..\n" +
                        "###...###...###...###...###...###...###.\n" +
                        "####....####....####....####....####....\n" +
                        "#####.....#####.....#####.....#####.....\n" +
                        "######......######......######......####\n" +
                        "#######.......#######.......#######....."
                ),
                Arguments.arguments(
                        "tenth/input.txt",
                        "###..#.....##..####.#..#..##..####..##..\n" +
                        "#..#.#....#..#.#....#.#..#..#....#.#..#.\n" +
                        "#..#.#....#....###..##...#..#...#..#....\n" +
                        "###..#....#.##.#....#.#..####..#...#.##.\n" +
                        "#....#....#..#.#....#.#..#..#.#....#..#.\n" +
                        "#....####..###.#....#..#.#..#.####..###."
                )
        );
    }
}
