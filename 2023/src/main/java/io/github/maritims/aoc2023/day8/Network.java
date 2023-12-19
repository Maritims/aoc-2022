package io.github.maritims.aoc2023.day8;

import java.util.Map;

public class Network {
    private final Map<String, Node> nodes;

    public Network(Map<String, Node> nodes) {
        this.nodes = nodes;
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }
}
