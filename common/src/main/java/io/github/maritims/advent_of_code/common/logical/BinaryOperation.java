package io.github.maritims.advent_of_code.common.logical;

public interface BinaryOperation extends Operation {
    int evaluate(int a, int b);

    /**
     * Evaluates the binary operation using the provided operands.
     *
     * @param operands the input values as strings. This method requires exactly two operands,
     *                 which will be parsed as integers before evaluation.
     * @return the result of the operation as an integer.
     * @throws IllegalArgumentException if the number of operands is not exactly two, or if
     *                                  the operands cannot be parsed as integers.
     */
    @Override
    default int evaluate(String... operands) {
        if (operands.length != 2) {
            throw new IllegalArgumentException("BinaryOperation requires exactly two operands");
        }
        return evaluate(Integer.parseInt(operands[0]), Integer.parseInt(operands[1]));
    }
}
