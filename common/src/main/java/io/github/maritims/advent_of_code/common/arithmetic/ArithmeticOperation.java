package io.github.maritims.advent_of_code.common.arithmetic;

@FunctionalInterface
public interface ArithmeticOperation {
    long evaluate(long a, long b);

    static ArithmeticOperation fromOperator(char operator) {
        return switch (operator) {
            case '+' -> new Add();
            case '-' -> new Subtract();
            case '*' -> new Multiply();
            case '/' -> new Divide();
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }
}
