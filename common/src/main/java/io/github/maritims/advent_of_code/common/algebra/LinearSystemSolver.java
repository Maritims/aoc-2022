package io.github.maritims.advent_of_code.common.algebra;

import java.util.stream.IntStream;

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
            if (Math.abs(matrix[pivot][pivot]) < 1e-12) {
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

    public static void toReducedRowEchelonForm(double[][] matrix, int unknowns) {
        var rows     = matrix.length;
        var pivotRow = 0;

        // Find a pivot.
        for (var pivotCol = 0; pivotCol < unknowns && pivotRow < rows; pivotCol++) {
            // Partial pivoting.
            var max = pivotRow;
            for (var row = pivotRow + 1; row < rows; row++) {
                if (Math.abs(matrix[row][pivotCol]) > Math.abs(matrix[max][pivotCol])) {
                    max = row;
                }
            }

            // Skip if mostly (almost) zeros.
            if (Math.abs(matrix[max][pivotCol]) < 1e-12) {
                continue;
            }

            // Swap rows.
            var temp = matrix[pivotRow];
            matrix[pivotRow] = matrix[max];
            matrix[max]      = temp;

            // Normalize.
            var divisor = matrix[pivotRow][pivotCol];
            for (var col = pivotCol; col < matrix[pivotRow].length; col++) {
                matrix[pivotRow][col] /= divisor;
            }

            for (var row = 0; row < rows; row++) {
                if (row != pivotRow && Math.abs(matrix[row][pivotCol]) > 1e-12) {
                    var factor = matrix[row][pivotCol];
                    for (var col = pivotCol; col < matrix[row].length; col++) {
                        matrix[row][col] -= factor * matrix[pivotRow][col];
                    }
                }
            }

            // Next pivot.
            pivotRow++;
        }
    }

    public static int[] findFreeVariableIndices(double[][] reducedRowEchelonForm, int numberOfVariables) {
        var isPivotColumn = new boolean[numberOfVariables];

        var currentRow = 0;
        for (var col = 0; col < numberOfVariables && currentRow < reducedRowEchelonForm.length; col++) {
            if (Math.abs(reducedRowEchelonForm[currentRow][col] - 1.0) < 1e-12) {
                isPivotColumn[col] = true;
                currentRow++;
            }
        }

        return IntStream.range(0, numberOfVariables)
                .filter(col -> !isPivotColumn[col])
                .toArray();
    }

    /**
     * Use Gaussian elimination to solve the matrix.
     */
    public static double[] solve(double[][] matrix, int unknowns) {
        //toRowEchelonForm(matrix);
        toReducedRowEchelonForm(matrix, unknowns);

        double[] result     = new double[unknowns];
        int      currentRow = 0;
        for (int col = 0; col < unknowns && currentRow < matrix.length; col++) {
            // Is the column a pivot?
            if (Math.abs(matrix[currentRow][col] - 1.0) < 1e-12) {
                result[col] = matrix[currentRow][matrix[currentRow].length - 1];
                currentRow++;
            }
        }

        return result;
        //return substituteBackwards(matrix);
    }
}
