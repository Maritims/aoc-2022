package io.github.maritims.aoc2023.day8;

import java.util.Map;

public class Network {
    private final String instructions;
    private final Map<String, Node> nodes;

    public Network(String instructions, Map<String, Node> nodes) {
        this.instructions = instructions;
        this.nodes = nodes;
    }

    public int traverse(String startingPosition, String targetPosition) {
        var currentNode = nodes.get(startingPosition);

        var steps = 0;
        while(!targetPosition.equalsIgnoreCase(currentNode.getName())) {
            var instruction = instructions.charAt(steps % instructions.length());
            var nameOfNextNode = instruction == 'L' ? currentNode.getLeft() : currentNode.getRight();
            currentNode = nodes.get(nameOfNextNode);
            steps++;
        }
        return steps;
    }
}
