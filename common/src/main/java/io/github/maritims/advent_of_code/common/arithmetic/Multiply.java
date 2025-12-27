package io.github.maritims.advent_of_code.common.arithmetic;

public class Multiply implements ArithmeticOperation {
    @Override
    public long evaluate(long a, long b) {
        return Math.multiplyExact(a, b);
    }
}
