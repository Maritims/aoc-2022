package io.github.maritims.advent_of_code.common.logical;

public final class And implements BinaryOperation {
    @Override
    public int evaluate(int a, int b) {
        return a & b;
    }
}
