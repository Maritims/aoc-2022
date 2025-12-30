package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;

import java.util.*;
import java.util.stream.Collectors;

public class Day11 extends PuzzleSolver<Long, Long> {
    /**
     * Count all the paths in the directed acyclic graph (DAG).
     *
     * @param graph       A directed acyclic graph.
     * @param memo        Memoization map.
     * @param current     Where we're at.
     * @param destination WHere we're going.
     * @return The number of all paths leading from current to destination.
     */
    private static long countAllPaths(Map<String, List<String>> graph, Map<String, Long> memo, String current, String destination) {
        if (current.equals(destination)) {
            return 1;
        }

        if (memo.containsKey(current)) {
            return memo.get(current);
        }

        var neighbours = graph.getOrDefault(current, new ArrayList<>());
        var count      = neighbours.stream().mapToLong(neighbour -> countAllPaths(graph, memo, neighbour, destination)).sum();
        memo.put(current, count);
        return count;
    }

    private static Map<String, List<String>> buildDAG(List<String> lines) {
        return lines.stream()
                .map(line -> line.split(" "))
                .map(parts -> {
                    var input   = parts[0].substring(0, parts[0].length() - 1);
                    var outputs = Arrays.stream(parts).skip(1).toList();
                    return Map.entry(input, outputs);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static long countPathsBetween(Map<String, List<String>> graph, String start, String end) {
        return countAllPaths(graph, new HashMap<>(), start, end);
    }

    @Override
    public Long solveFirstPart() {
        var graph = buildDAG(loadInput());
        var memo  = new HashMap<String, Long>();
        return countAllPaths(graph, memo, "you", "out");
    }

    @Override
    public Long solveSecondPart() {
        var graph = buildDAG(loadInput());
        var path1 = countPathsBetween(graph, "svr", "dac") * countPathsBetween(graph, "dac", "fft") * countPathsBetween(graph, "fft", "out");
        var path2 = countPathsBetween(graph, "svr", "fft") * countPathsBetween(graph, "fft", "dac") * countPathsBetween(graph, "dac", "out");
        return path1 + path2;
    }
}
