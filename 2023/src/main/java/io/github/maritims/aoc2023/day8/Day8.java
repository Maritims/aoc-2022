package io.github.maritims.aoc2023.day8;

import io.github.maritims.toolbox.Day;
import io.github.maritims.toolbox.MathUtil;

import java.util.function.Predicate;
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

    protected long traverse(Network network, String instructions, String startingPosition, Predicate<Node> winCondition) {
        var             currentNode  = network.getNodes().get(startingPosition);

        var steps = 0;
        while (!winCondition.test(currentNode)) {
            var instruction    = instructions.charAt(steps % instructions.length());
            var nameOfNextNode = instruction == 'L' ? currentNode.getLeft() : currentNode.getRight();
            currentNode = network.getNodes().get(nameOfNextNode);
            steps++;
        }
        return steps;
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
        return traverse(network, instructions, "AAA", node -> "ZZZ".equalsIgnoreCase(node.getName()));
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

        var ghosts = network.getNodes()
            .keySet()
            .stream()
            .filter(key -> key.endsWith("A"))
            .map(Ghost::new)
            .toList();

        var cycles = ghosts.stream()
            .map(ghost -> traverse(network, instructions, ghost.getStartingPosition(), node -> node.getName().endsWith("Z")))
            .toArray(Long[]::new);
        return MathUtil.lcm(cycles);
    }
}