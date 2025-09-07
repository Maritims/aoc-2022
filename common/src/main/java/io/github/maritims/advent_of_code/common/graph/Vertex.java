package io.github.maritims.advent_of_code.common.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Vertex {
    private final int                id;
    private final Map<Integer, Edge> edges = new HashMap<>();

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Set<Edge> getEdges() {
        return Set.copyOf(edges.values());
    }

    public void connect(Vertex other, int weight) {
        edges.put(other.id, new Edge(other, weight));
        other.edges.put(id, new Edge(this, weight));
    }

    @Override
    public String toString() {
        return "Vertex{" +
               "id=" + id +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        var vertex = (Vertex) o;
        return id == vertex.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
