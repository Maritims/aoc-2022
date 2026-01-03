package io.github.maritims.advent_of_code.common.graph;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;

public final class AdjacencyMatrix<T, W extends Number> {
    public record Node<T, W extends Number>(T from, T to, W weight) {
    }

    @FunctionalInterface
    public interface OriginExtractor<T> {
        T extractOrigin(String input);
    }

    @FunctionalInterface
    public interface DestinationExtractor<T> {
        T extractDestination(String input);
    }

    @FunctionalInterface
    public interface WeightExtractor<W> {
        W extractWeight(String input);
    }

    @FunctionalInterface
    public interface NodeExtractor<T, W extends Number> {
        List<Node<T, W>> extractNodes(String input);
    }

    @FunctionalInterface
    public interface WeightCombiner<W extends Number> {
        W combineWeights(W w1, W w2);
    }

    private final List<Node<T, W>> nodes;
    private final Class<W>         weightType;
    private final W[][]            matrix;

    public AdjacencyMatrix(List<Node<T, W>> nodes, Class<W> weightType, WeightCombiner<W> weightCombiner) {
        this.nodes      = nodes;
        this.weightType = weightType;

        var nodeValueToIndex = new LinkedHashMap<T, Integer>();
        nodes.forEach(node -> nodeValueToIndex.putIfAbsent(node.from(), nodeValueToIndex.size()));
        //noinspection unchecked
        this.matrix = (W[][]) Array.newInstance(weightType, nodeValueToIndex.size(), nodeValueToIndex.size());

        for (var node : nodes) {
            var fromIndex = Objects.requireNonNull(nodeValueToIndex.get(node.from()), "no index exists for node value \"" + node.from() + "\" (quotes added)");
            var toIndex   = Objects.requireNonNull(nodeValueToIndex.get(node.to()), "no index exists for node value \"" + node.to() + "\" (quotes added)");

            this.matrix[fromIndex][toIndex] = this.matrix[fromIndex][toIndex] == null ? node.weight() : weightCombiner.combineWeights(this.matrix[fromIndex][toIndex], node.weight());
            this.matrix[toIndex][fromIndex] = this.matrix[toIndex][fromIndex] == null ? node.weight() : weightCombiner.combineWeights(this.matrix[toIndex][fromIndex], node.weight());
        }
    }

    public static <T, W extends Number> AdjacencyMatrix<T, W> parseAdjacencyMatrix(
            List<String> lines,
            Class<W> weightType,
            Supplier<OriginExtractor<T>> originExtractor,
            Supplier<DestinationExtractor<T>> destinationExtractor,
            Supplier<WeightExtractor<W>> weightExtractor,
            WeightCombiner<W> weightCombiner,
            Consumer<List<Node<T, W>>> onNodeAdded
    ) {
        var nodes = new ArrayList<Node<T, W>>();

        for (var line : lines) {
            var from   = originExtractor.get().extractOrigin(line);
            var to     = destinationExtractor.get().extractDestination(line);
            var weight = weightExtractor.get().extractWeight(line);
            var node   = new AdjacencyMatrix.Node<>(from, to, weight);

            nodes.add(node);

            if (onNodeAdded != null) {
                onNodeAdded.accept(nodes);
            }
        }

        return new AdjacencyMatrix<>(nodes, weightType, weightCombiner);
    }

    public static <T, W extends Number> AdjacencyMatrix<T, W> parseAdjacencyMatrix(
            List<String> lines,
            Class<W> weightType,
            Supplier<NodeExtractor<T, W>> nodeExtractor,
            WeightCombiner<W> weightCombiner
    ) {
        var nodes = lines.stream()
                .map(line -> nodeExtractor.get().extractNodes(line))
                .flatMap(Collection::stream)
                .toList();
        return new AdjacencyMatrix<>(nodes, weightType, weightCombiner);
    }

    public W[][] getMatrix() {
        //noinspection unchecked
        var clonedMatrix = (W[][]) Array.newInstance(weightType, matrix.length, matrix.length);
        IntStream.range(0, matrix.length).forEach(i -> System.arraycopy(matrix[i], 0, clonedMatrix[i], 0, matrix.length));
        return clonedMatrix;
    }
}