package io.github.maritims.aoc2023.day8;

import io.github.maritims.toolbox.Day;

import java.util.stream.Collectors;

public class Day8 extends Day {

    protected Day8(boolean useSampleData) {
        super(8, useSampleData);
    }

    protected Node toNode(String line) {
        var name = line.substring(0, 3);
        var left = line.substring(7, 10);
        var right = line.substring(12, 15);
        return new Node(name, left, right);
    }

    @Override
    public long solvePartOne() {
        var lines        = readAllLines();
        var instructions = lines.remove(0);
        lines.remove(0);
        return new Network(instructions, lines.stream().map(this::toNode).collect(Collectors.toMap(Node::getName, node -> node))).traverse("AAA", "ZZZ");
    }

    @Override
    public int solvePartTwo() {
        return 0;
    }
}