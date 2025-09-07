package io.github.maritims.advent_of_code.common.graph;

import java.util.Arrays;

public class TravellingSalesman {
    public enum Strategy {
        LowestCost, HighestCost
    }

    public static final int UNSET = -1;

    private final int[][]  distanceMatrix;
    private final Strategy strategy;
    private final int[][]  cache;
    private final int      totalNodes;

    public TravellingSalesman(int[][] distanceMatrix, Strategy strategy) {
        this.distanceMatrix = distanceMatrix;
        this.totalNodes     = distanceMatrix.length;
        this.strategy       = strategy;
        this.cache          = new int[1000][1000];

        Arrays.stream(cache).forEach(ints -> Arrays.fill(ints, UNSET));
    }

    private void resetCache() {
        Arrays.stream(cache).forEach(ints -> Arrays.fill(ints, UNSET));
    }

    private boolean hasVisitedAllNodes(int visitedMask) {
        return visitedMask == (1 << totalNodes) - 1;
    }

    private boolean isVisited(int visitedMask, int currentNode) {
        return (visitedMask & (1 << currentNode)) != 0;
    }

    private int visit(int visitedMask, int currentNode) {
        return visitedMask | (1 << currentNode);
    }

    private boolean isCached(int visitedMask, int currentNode) {
        return cache[visitedMask][currentNode] != -1;
    }

    private int getCached(int visitedMask, int currentNode) {
        return cache[visitedMask][currentNode];
    }

    private int putInCache(int visitedMask, int currentNode, int value) {
        return cache[visitedMask][currentNode] = value;
    }

    private int solve(int visitedMask, int currentNode) {
        if (hasVisitedAllNodes(visitedMask)) {
            return 0;
        }

        if (isCached(visitedMask, currentNode)) {
            return getCached(visitedMask, currentNode);
        }

        var bestCost = switch (strategy) {
            case LowestCost -> Integer.MAX_VALUE;
            case HighestCost -> Integer.MIN_VALUE;
        };

        for (var nextNode = 0; nextNode < totalNodes; nextNode++) {
            if (!isVisited(visitedMask, nextNode)) {
                var newMask = visit(visitedMask, nextNode);
                var cost    = distanceMatrix[currentNode][nextNode] + solve(newMask, nextNode);

                bestCost = switch (strategy) {
                    case LowestCost -> Math.min(bestCost, cost);
                    case HighestCost -> Math.max(bestCost, cost);
                };
            }
        }

        return putInCache(visitedMask, currentNode, bestCost);
    }

    public int compute() {
        resetCache();

        var bestOverallCost = switch (strategy) {
            case LowestCost -> Integer.MAX_VALUE;
            case HighestCost -> Integer.MIN_VALUE;
        };

        for (var startNode = 0; startNode < distanceMatrix.length; startNode++) {
            var cost = solve(1 << startNode, startNode);
            bestOverallCost = switch (strategy) {
                case LowestCost -> Math.min(bestOverallCost, cost);
                case HighestCost -> Math.max(bestOverallCost, cost);
            };
        }

        return bestOverallCost;
    }
}
