package io.github.maritims.advent_of_code.common.arithmetic;

public class Divide implements ArithmeticOperation {
    @Override
    public long evaluate(long a, long b) {
        return Math.divideExact(a, b);
    }
}
