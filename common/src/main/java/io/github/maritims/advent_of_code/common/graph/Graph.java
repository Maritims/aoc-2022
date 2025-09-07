package io.github.maritims.advent_of_code.common.graph;

import io.github.maritims.advent_of_code.common.tuples.Tuple3;

import java.util.*;

public class Graph {
    private final Map<Integer, Vertex> vertices = new HashMap<>();

    private Graph(Map<Integer, Vertex> vertices) {
        this.vertices.putAll(vertices);
    }

    public int[][] getDistanceMatrix() {
        var size      = vertices.size();
        var matrix    = new int[size][size];
        var idToIndex = new HashMap<Integer, Integer>();
        var idx       = 0;
        for (var vertex : vertices.values()) {
            idToIndex.put(vertex.getId(), idx++);
        }

        for (var vertex : vertices.values()) {
            var row = idToIndex.get(vertex.getId());
            for (var edge : vertex.getEdges()) {
                var col = idToIndex.get(edge.vertex().getId());
                matrix[row][col] = edge.weight();
                matrix[col][row] = edge.weight();
            }
        }

        return matrix;
    }

    @FunctionalInterface
    public interface Resolver<T> {
        Tuple3<Vertex, Vertex, Integer> resolve(T input);
    }

    public static class Builder<T> {
        private final Map<Integer, Vertex> vertices = new HashMap<>();
        private       Resolver<T>          resolver;

        private Builder() {
        }

        public Builder<T> resolver(Resolver<T> resolver) {
            this.resolver = resolver;
            return this;
        }

        public Builder<T> addVertices(Vertex fromVertex, Vertex toVertex, int weight) {
            vertices.computeIfAbsent(fromVertex.getId(), Vertex::new).connect(toVertex, weight);
            vertices.computeIfAbsent(toVertex.getId(), Vertex::new).connect(fromVertex, weight);
            return this;
        }

        private void addVertices(T input) {
            var tuple      = resolver.resolve(input);
            var fromVertex = tuple.item1();
            var toVertex   = tuple.item2();
            var weight     = tuple.item3();

            addVertices(fromVertex, toVertex, weight);
        }

        public Builder<T> addVertices(List<T> inputs) {
            inputs.forEach(this::addVertices);
            return this;
        }

        public Graph build() {
            return new Graph(vertices);
        }
    }

    public static <T> Builder<T> newBuilder() {
        return new Builder<>();
    }
}
