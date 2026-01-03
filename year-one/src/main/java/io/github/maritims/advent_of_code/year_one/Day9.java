package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.graph.*;
import io.github.maritims.advent_of_code.common.util.IntegerUtil;

import java.util.List;

public class Day9 extends PuzzleSolver<Integer, Integer> {
    private static final class Extractor implements AdjacencyMatrix.OriginExtractor<String>, AdjacencyMatrix.DestinationExtractor<String>, AdjacencyMatrix.WeightExtractor<Integer>, AdjacencyMatrix.NodeExtractor<String, Integer> {
        String[]    parts         = null;
        int         numberOfParts = 0;

        String[] getParts(String input) {
            if (parts == null) {
                parts         = input.split(" ");
                numberOfParts = parts.length;
            }
            return parts;
        }

        @Override
        public String extractDestination(String input) {
            return getParts(input)[2];
        }

        @Override
        public String extractOrigin(String input) {
            return getParts(input)[0];
        }

        @Override
        public Integer extractWeight(String input) {
            return Integer.parseInt(getParts(input)[numberOfParts - 1]);
        }

        @Override
        public List<AdjacencyMatrix.Node<String, Integer>> extractNodes(String input) {
            var origin      = extractOrigin(input);
            var destination = extractDestination(input);
            var weight = extractWeight(input);

            return List.of(
                    new AdjacencyMatrix.Node<>(origin, destination, weight),
                    new AdjacencyMatrix.Node<>(destination, origin, weight)
            );
        }
    }

    @Override
    public Integer solveFirstPart() {
        var adjacencyMatrix = AdjacencyMatrix.parseAdjacencyMatrix(loadInput(), Integer.class, Extractor::new, (a, b) -> a);
        var matrix          = IntegerUtil.toPrimitiveArray(adjacencyMatrix.getMatrix(), 0);
        return HamiltonianPathSolver.solverForMinCost(matrix).doNotReturnHome().solveHamiltonianPath();
    }

    @Override
    public Integer solveSecondPart() {
        var adjacencyMatrix = AdjacencyMatrix.parseAdjacencyMatrix(loadInput(), Integer.class, Extractor::new, (a, b) -> a);
        var matrix          = IntegerUtil.toPrimitiveArray(adjacencyMatrix.getMatrix(), 0);
        return HamiltonianPathSolver.solverForMaxCost(matrix).doNotReturnHome().solveHamiltonianPath();
    }
}
