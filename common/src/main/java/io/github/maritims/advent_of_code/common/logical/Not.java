package io.github.maritims.advent_of_code.common.logical;

public final class Not implements UnaryOperation {
    @Override
    public int evaluate(int a) {
        return ~a;
    }
}
