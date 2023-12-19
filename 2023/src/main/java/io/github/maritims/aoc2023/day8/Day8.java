package io.github.maritims.aoc2023.day8;

import io.github.maritims.toolbox.Day;
import io.github.maritims.toolbox.MathUtil;

import java.util.stream.Collectors;

public class Day8 extends Day {

    protected Day8(boolean useSampleData) {
        super(8, useSampleData);
    }

    protected Node toNode(String line) {
        var name  = line.substring(0, 3);
        var left  = line.substring(7, 10);
        var right = line.substring(12, 15);
        return new Node(name, left, right);
    }

    @Override
    public long solvePartOne() {
        var lines        = readAllLines();
        var instructions = lines.remove(0);
        lines.remove(0);

        var network = new Network(
            lines.stream()
                .map(this::toNode)
                .collect(Collectors.toMap(Node::getName, node -> node))
        );
        return network.traverse(instructions, "AAA", node -> "ZZZ".equalsIgnoreCase(node.getName()));
    }

    @Override
    public long solvePartTwo() {
        var lines        = useSampleData ? readAllLines("day8_part2_sample.txt") : readAllLines();
        var instructions = lines.remove(0);
        lines.remove(0);

        var network = new Network(
            lines.stream()
                .map(this::toNode)
                .collect(Collectors.toMap(Node::getName, node -> node))
        );

        var ghosts = network.nodes()
            .keySet()
            .stream()
            .filter(key -> key.endsWith("A"))
            .map(Ghost::new)
            .toList();

        // Given an infinite amount of time all ghosts will run in a never-ending loop along their own path.
        // The path of a ghost always takes exactly the same number of steps.
        // If we first determine how many steps the path of each ghost takes, we can use Euclid's algorithm for finding the greatest common divisor to then find the least common multiple of all these cycles.
        // The least common multiple of all these cycles is how many steps it takes before each ghost is at an end position simultaneously.
        var cycles = ghosts.stream()
            .map(ghost -> network.traverse(instructions, ghost.getStartingPosition(), node -> node.getName().endsWith("Z")))
            .toArray(Long[]::new);

        return MathUtil.lcm(cycles);
    }
}