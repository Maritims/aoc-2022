package io.github.maritims.advent_of_code.common.graph;

import java.util.function.IntBinaryOperator;

public class HamiltonianPathSolver {
    private final int[][]           matrix;
    private final IntBinaryOperator decisionFunction;
    private final Integer[][]       cache;
    private final int               epsilon;
    private       boolean           returnHome;

    private HamiltonianPathSolver(int[][] matrix, IntBinaryOperator decisionFunction, int epsilon, boolean returnHome) {
        this.matrix           = matrix;
        this.decisionFunction = decisionFunction;
        this.cache            = new Integer[matrix.length][1 << matrix.length];
        this.epsilon          = epsilon;
        this.returnHome       = returnHome;
    }

    public static HamiltonianPathSolver solverForMaxCost(int[][] matrix) {
        return new HamiltonianPathSolver(matrix, Math::max, -1_000_000, true);
    }

    public static HamiltonianPathSolver solverForMinCost(int[][] matrix) {
        return new HamiltonianPathSolver(matrix, Math::min, 1_000_000, true);
    }

    public HamiltonianPathSolver doNotReturnHome() {
        returnHome = false;
        return this;
    }

    protected final int solve(int currentNode, int visitedNodes) {
        var totalNodes = matrix.length;

        // A trick for creating a number where every bit representing a node is set.
        // Left shifting 1 creates a power of 2. Subtracting from a power of 2 flips the bits so that the MSB becomes 0 and the lower bits become 1!
        var allVisited = (1 << totalNodes) - 1;
        if (visitedNodes == allVisited) {
            return returnHome ? matrix[currentNode][0] : 0;
        }

        if (cache[currentNode][visitedNodes] != null) {
            return cache[currentNode][visitedNodes];
        }

        // Use a low epsilon to prevent decimal precision trouble.
        var finalCost = epsilon;

        for (var neighbour = 0; neighbour < totalNodes; neighbour++) {
            // Left shift 1 by neighbour to get a power of 2 where the only set bit is the node's position in the matrix.
            var neighbourBit = 1 << neighbour;

            // AND-ing the visitedNodes and neighbourBit will be 0 if we've never visited the neighbour.
            var hasNotVisitedNeighbour = (visitedNodes & neighbourBit) == 0;

            // OR-ing the visitedNodes and neighbourBit will set the neighbour bit of the visited nodes to 1.
            if (hasNotVisitedNeighbour) {
                var newVisitedStatus = visitedNodes | neighbourBit;

                // Find the cost with this neighbour as the starting node.
                var currentCost = matrix[currentNode][neighbour] + solve(neighbour, newVisitedStatus);
                finalCost = decisionFunction.applyAsInt(finalCost, currentCost);
            }
        }

        return cache[currentNode][visitedNodes] = finalCost;
    }

    public final int solveHamiltonianCycle() {
        return solve(0, 1);
    }

    public final int solveHamiltonianPath() {
        var absoluteBest = epsilon;
        for(var i = 0; i < matrix.length; i++) {
            absoluteBest = decisionFunction.applyAsInt(absoluteBest, solve(i, 1 << i));
        }
        return absoluteBest;
    }
}
