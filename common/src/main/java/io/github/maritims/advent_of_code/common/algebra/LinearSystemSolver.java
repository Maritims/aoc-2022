package io.github.maritims.advent_of_code.common.algebra;

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
        for (var pivot = 0; pivot < matrix.length; pivot++) {
            // Partial pivoting: Find the greatest number in the column "pivot" from row "pivot" and downwards.
            var max = pivot;
            for (var row = pivot + 1; row < matrix.length; row++) {
                if (Math.abs(matrix[row][pivot]) > Math.abs(matrix[max][pivot])) {
                    max = row;
                }
            }

            // Swap rows if we're not currently on the best row.
            var temp = matrix[pivot];
            matrix[pivot] = matrix[max];
            matrix[max]   = temp;


            // Emergency brake: We can't continue if max is (almost) zero.
            if(Math.abs(matrix[pivot][pivot]) < 1e-12) {
                // No valid pivots in the column. The matrix is invalid somehow.
                continue;
            }

            // Walk down across the rows, but remember that we start with the first row beneath the pivot row number, defined as "pivot".
            for (var row = pivot + 1; row < matrix.length; row++) {
                // The factor is the existing value of the column we're currently working with, defined as "k", divided by the pivot value (matrix[k][k]).

                var factor = matrix[row][pivot] / matrix[pivot][pivot];

                // Walk left to right across the columns.
                for (var col = pivot; col < matrix[row].length; col++) {
                    // When you augment a row in the matrix, remember to inspect the corresponding column of the pivot row (matrix[k]) to understand how much you must subtract (matrix[k][col]).
                    matrix[row][col] -= factor * matrix[pivot][col];
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

    /**
     * Use Gaussian elimination to solve the matrix.
     */
    public static double[] solve(double[][] matrix) {
        toRowEchelonForm(matrix);
        return substituteBackwards(matrix);
    }
}
