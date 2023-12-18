package io.github.maritims.aoc2023.day8;

public class Node {
    private final String name;
    private final String left;
    private final String right;

    public Node(String name, String left, String right) {
        this.name  = name;
        this.left  = left;
        this.right = right;
    }

    public String getName() {
        return name;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }
}
