package io.github.maritims.advent_of_code.common.arithmetic;

public final class Add implements ArithmeticOperation {
    @Override
    public long evaluate(long a, long b) {
        return Math.addExact(a, b);
    }
}
