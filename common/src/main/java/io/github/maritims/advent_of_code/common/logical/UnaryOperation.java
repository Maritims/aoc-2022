package io.github.maritims.advent_of_code.common.logical;

public interface UnaryOperation extends Operation {
    int evaluate(int a);

    /**
     * Evaluates the unary operation using the provided operand.
     *
     * @param operands the input values as strings. This method requires exactly one operand,
     *                 which will be parsed as an integer before evaluation.
     * @return the result of the operation as an integer.
     * @throws IllegalArgumentException if the number of operands is not exactly one, or if
     *                                  the operand cannot be parsed as an integer.
     */
    @Override
    default int evaluate(String... operands) {
        if (operands.length != 1) {
            throw new IllegalArgumentException("UnaryOperation requires exactly one operand");
        }
        return evaluate(Integer.parseInt(operands[0]));
    }
}
