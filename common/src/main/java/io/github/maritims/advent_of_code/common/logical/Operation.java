package io.github.maritims.advent_of_code.common.logical;

public interface Operation {
    /**
     * Evaluates the operation using the given operands.
     *
     * @param operands the input values as strings. The number of operands required
     *                 depends on the specific operation implementation. Unary operations
     *                 require one operand, while binary operations require two operands.
     * @return the result of the operation as an integer.
     * @throws IllegalArgumentException if the number of operands is incorrect, or if
     *                                  the operands cannot be parsed as integers.
     */
    int evaluate(String... operands);
}
