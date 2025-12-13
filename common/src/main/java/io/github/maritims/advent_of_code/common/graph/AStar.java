package io.github.maritims.advent_of_code.common.graph;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Supplier;

public class AStar<TNode> {
    public interface State {
        List<State> getSuccessors();

        int getCost();

        boolean isGoal();
    }

    public interface Heuristic {
        int estimate(State state);
    }

    public static class Node implements Comparable<Node> {
        private final State state;
        private final int   g;
        private final int   f;

        public Node(State state, int g, int f) {
            this.state = state;
            this.g     = g;
            this.f     = f;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(f, other.f);
        }
    }

    public State search(State start, Heuristic heuristic, Supplier<TNode> startingNodeSupplier) {
        var open   = new PriorityQueue<Node>();
        var closed = new HashSet<State>();

        // Start with empty seating.
        open.add(new Node(start, 0, heuristic.estimate(start)));

        State best = null;
        var bestF = Integer.MAX_VALUE;

        while (!open.isEmpty()) {
            var current = open.poll();

            if (current.state.isGoal()) {
                //var f =
                return current.state;
            }

            if (closed.contains(current.state)) {
                continue;
            }
            closed.add(current.state);

            for (var successor : current.state.getSuccessors()) {
                if (closed.contains(successor)) {
                    continue;
                }

                var g = successor.getCost();
                var f = g + heuristic.estimate(successor);
                open.add(new Node(successor, g, f));
            }
        }

        return null;
    }
}
