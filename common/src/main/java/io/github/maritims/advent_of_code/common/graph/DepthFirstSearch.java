package io.github.maritims.advent_of_code.common.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.github.maritims.advent_of_code.common.util.ArgumentExceptionUtil.throwIfNull;

public class DepthFirstSearch<TNode, TValue> {
    private       NeighbourProvider<TNode> neighbourProvider;
    private final Map<TNode, TValue>       cache = new HashMap<>();

    public DepthFirstSearch(NeighbourProvider<TNode> neighbourProvider) {
        this.neighbourProvider = throwIfNull(neighbourProvider, "neighbourProvider");
    }

    public DepthFirstSearch<TNode, TValue> reset() {
        cache.clear();
        return this;
    }

    public DepthFirstSearch<TNode, TValue> setNeighbourProvider(NeighbourProvider<TNode> neighbourProvider) {
        this.neighbourProvider = throwIfNull(neighbourProvider, "neighbourProvider");
        return this;
    }

    public TValue evaluate(TNode node, Function<TNode, TValue> evaluator) {
        throwIfNull(node, "node");
        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        var value = evaluator.apply(node);

        for (var neighbour : neighbourProvider.getNeighbours(node)) {
            evaluate(neighbour, evaluator);
        }

        cache.put(node, value);
        return value;
    }
}
