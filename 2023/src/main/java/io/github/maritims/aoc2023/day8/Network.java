package io.github.maritims.aoc2023.day8;

import java.util.Map;
import java.util.function.Predicate;

public record Network(Map<String, Node> nodes) {
    /**
     * Get the number of steps required from the starting position until the win condition is met.
     */
    public long traverse(String instructions, String startingPosition, Predicate<Node> winCondition) {
        var currentNode = nodes().get(startingPosition);

        var steps = 0;
        while (!winCondition.test(currentNode)) {
            var instruction    = instructions.charAt(steps % instructions.length());
            var nameOfNextNode = instruction == 'L' ? currentNode.left() : currentNode.right();
            currentNode = nodes().get(nameOfNextNode);
            steps++;
        }
        return steps;
    }
}