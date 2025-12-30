package io.github.maritims.advent_of_code.common.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Function;

public class BreadthFirstSearch<T> {
    public record Step<T>(T state, int distance) {
    }

    public int findShortestPath(T start, Function<T, Boolean> isTarget, Function<T, Iterable<T>> getNeighbours) {
        if (isTarget.apply(start)) {
            return 0;
        }

        var queue   = new LinkedList<Step<T>>();
        var visited = new HashSet<T>();

        queue.add(new Step<>(start, 0));
        visited.add(start);

        while (!queue.isEmpty()) {
            var current = queue.poll();

            for(var neighbour : getNeighbours.apply(current.state)) {
                if (isTarget.apply(neighbour)) {
                    return current.distance + 1;
                }

                if(!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(new Step<>(neighbour, current.distance + 1));
                }
            }
        }

        return -1;
    }
}
