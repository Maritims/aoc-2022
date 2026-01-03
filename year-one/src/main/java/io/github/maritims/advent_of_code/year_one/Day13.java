package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.graph.AdjacencyMatrix;
import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.graph.HamiltonianPathSolver;
import io.github.maritims.advent_of_code.common.util.IntegerUtil;

import java.util.*;

public class Day13 extends PuzzleSolver<Integer, Integer> {
    private static final class Extractor implements AdjacencyMatrix.OriginExtractor<String>, AdjacencyMatrix.DestinationExtractor<String>, AdjacencyMatrix.WeightExtractor<Integer> {
        String[] parts         = null;
        int      numberOfParts = 0;

        String[] getParts(String input) {
            if (parts == null) {
                parts         = input.split(" ");
                numberOfParts = parts.length;
            }
            return parts;
        }

        @Override
        public String extractDestination(String input) {
            return getParts(input)[numberOfParts - 1].replace(".", "");
        }

        @Override
        public String extractOrigin(String input) {
            return getParts(input)[0];
        }

        @Override
        public Integer extractWeight(String input) {
            var modifier = "gain".equals(getParts(input)[2]) ? 1 : -1;
            return Integer.parseInt(getParts(input)[3]) * modifier;
        }
    }

    @Override
    public Integer solveFirstPart() {
        var adjacencyMatrix = AdjacencyMatrix.parseAdjacencyMatrix(loadInput(), Integer.class, Extractor::new, Extractor::new, Extractor::new, Integer::sum, null);
        var matrix          = IntegerUtil.toPrimitiveArray(adjacencyMatrix.getMatrix(), 0);
        return HamiltonianPathSolver.solverForMaxCost(matrix).solveHamiltonianCycle();
    }

    @Override
    public Integer solveSecondPart() {
        var yourNeighbours = new HashSet<String>();
        var adjacencyMatrix = AdjacencyMatrix.parseAdjacencyMatrix(loadInput(), Integer.class, Extractor::new, Extractor::new, Extractor::new, Integer::sum, (nodes) -> {
            var justAdded = nodes.getLast();
            if (!yourNeighbours.contains(justAdded.from())) {
                nodes.add(new AdjacencyMatrix.Node<>("you", justAdded.from(), 0));
                yourNeighbours.add(justAdded.from());
            }
        });
        var matrix = IntegerUtil.toPrimitiveArray(adjacencyMatrix.getMatrix(), 0);
        return HamiltonianPathSolver.solverForMaxCost(matrix).solveHamiltonianCycle();
    }
}
