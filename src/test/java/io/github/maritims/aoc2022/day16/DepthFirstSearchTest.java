package io.github.maritims.aoc2022.day16;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class DepthFirstSearchTest {
    @Test
    public void printAllPaths() {
        // arrange
        List<Valve> valves = Valve.fromFile("16/example.txt");
        DepthFirstSearch<Valve> sut = new DepthFirstSearch<>(valves);
        Valve first = valves.get(0);
        Valve last = valves.get(valves.size() - 1);

        // act
        List<Valve[]> paths = sut.getAllPaths(first, last);

        // assert
        paths.stream().map(Arrays::toString).forEach(System.out::println);
    }
}