package io.github.maritims.advent_of_code.common.arithmetic;

public class Subtract implements ArithmeticOperation {
    @Override
    public long evaluate(long a, long b) {
        return Math.subtractExact(a, b);
    }
}
