package io.github.maritims.advent_of_code.common.graph;

@FunctionalInterface
public interface NeighbourProvider<TNode> {
    Iterable<TNode> getNeighbours(TNode current);
}