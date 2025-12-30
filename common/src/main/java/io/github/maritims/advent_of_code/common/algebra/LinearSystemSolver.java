package io.github.maritims.advent_of_code.common.algebra;

import java.util.Arrays;

public class LinearSystemSolver {
    /**
     * Converts the matrix to Row Echelon Form.
     * <p>For each row in the matrix:
     * <ul>
     *     <li>Get the first row below the pivot row.</li>
     *     <li>Calculate the factor, which is defined as the existing value of the column we're currently working with, divided by the pivot value.</li>
     *     <li>Then, for each column in the current row subtract the factor multiplied by the existing value in the corresponding column of the pivot row.</li>
     * </ul>
     * </p>
     *
     * @param matrix The matrix to convert to Row Echelon Form.
     */
    public static void toRowEchelonForm(double[][] matrix) {
        for (var pivotRow = 0; pivotRow < matrix.length; pivotRow++) {
            // Establish the pivot value.
            var pivotValue = matrix[pivotRow][pivotRow];

            // Walk down across the rows, but remember that we start with the first row beneath the pivot row number, defined as "k".
            for (var row = pivotRow + 1; row < matrix.length; row++) {
                // The factor is the existing value of the column we're currently working with, defined as "k", divided by the pivot value (matrix[k][k]).

                var factor = matrix[row][pivotRow] / pivotValue;

                // Walk left to right across the columns.
                for (var col = pivotRow; col < matrix[row].length; col++) {
                    // When you augment a row in the matrix, remember to inspect the corresponding column of the pivot row (matrix[k]) to understand how much you must subtract (matrix[k][col]).
                    matrix[row][col] -= factor * matrix[pivotRow][col];
                }
            }
        }
    }

    public static double[] substituteBackwards(double[][] rowEchelonForm) {
        var result = new double[3];

        // Always start at the bottom row since that is the easiest one.
        for (var row = rowEchelonForm.length - 1; row >= 0; row--) {
            // Get the number from the result column, the right-most column.
            var rhs = rowEchelonForm[row][rowEchelonForm[row].length - 1];

            // Subtract the variables we already know the value of. Note that this is never executed for the first iteration of the outer loop.
            for (var col = row + 1; col < rowEchelonForm[row].length - 1; col++) {
                rhs -= rowEchelonForm[row][col] * result[col];
            }

            // Divide the right-hand side by the unknown variable we're looking for, our pivot.
            result[row] = rhs / rowEchelonForm[row][row];
        }

        return result;
    }

    public static double[] solve(double[][] matrix) {
        // Create matrix.


        // Step 1: Get to Row Echelon Form.
        // Remember: new value = old value (factor * value from pivot row)

        // Step 1.1
        // 3.0 - (3.0 * 1.0) = 0.0
        // 2.0 - (3.0 * 1.0) = -1.0
        // 5.0 - (3.0 * 1.0) = 2.0
        // -4.0 - (3.0 * 6.0) = -22.0

        toRowEchelonForm(matrix);
        var result = substituteBackwards(matrix);

        for (var i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(matrix[i]));
            System.out.println(result[i]);
        }

        return result;
    }
}
