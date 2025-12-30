package io.github.maritims.advent_of_code.common.algebra;

import java.util.Arrays;

public class SimpleILPSolver {
    private final double[][] reducedRowEchelonForm;
    private final int        numberOfVariables;
    private final int[]      pivotColAtRow;
    private final int[]      freeVariableIndices;

    private double   minimumTotalSum = Double.POSITIVE_INFINITY;
    private double[] bestSolution    = null;

    public SimpleILPSolver(double[][] reducedRowEchelonForm, int numberOfVariables) {
        this.reducedRowEchelonForm = reducedRowEchelonForm;
        this.numberOfVariables     = numberOfVariables;
        this.pivotColAtRow         = new int[reducedRowEchelonForm.length];
        this.freeVariableIndices   = LinearSystemSolver.findFreeVariableIndices(reducedRowEchelonForm, numberOfVariables);

        // VIKTIG: Finn ut hvilken kolonne som er pivot for hver rad
        Arrays.fill(pivotColAtRow, -1);
        int currentRow = 0;
        for (int col = 0; col < numberOfVariables && currentRow < reducedRowEchelonForm.length; col++) {
            // Sjekker om kolonnen er en pivot (første 1-tall i raden)
            if (Math.abs(reducedRowEchelonForm[currentRow][col] - 1.0) < 1e-11) {
                pivotColAtRow[currentRow] = col;
                currentRow++;
            }
        }
    }

    private boolean calculatePivots(double[] currentSolution) {
        for(var row = 0; row < reducedRowEchelonForm.length; row++) {
            var pivotCol = pivotColAtRow[row];
            if(pivotCol == -1) {
                if (Math.abs(reducedRowEchelonForm[row][numberOfVariables]) > 1e-9) {
                    return false;
                }
                continue;
            }

            var value = reducedRowEchelonForm[row][numberOfVariables];
            for(var col = 0; col < numberOfVariables; col++) {
                if (col != pivotCol) {
                    value -= reducedRowEchelonForm[row][col] * currentSolution[col];
                }
            }

            var rounded = Math.round(value);
            if (value < -1e-9 || Math.abs(value - rounded) > 1e-8) {
                return false;
            }

            currentSolution[pivotCol] = rounded;
        }

        return true;
    }

    private void backtrack(int freeVariableIndex, double[] currentSolution, int maxGuess) {
        if(freeVariableIndex == freeVariableIndices.length) {
            if(calculatePivots(currentSolution)) {
                var currentSum = Arrays.stream(currentSolution).sum();

                if(currentSum < minimumTotalSum) {
                    minimumTotalSum = currentSum;
                    bestSolution = currentSolution.clone();
                }
            }
            return;
        }

        var variableIndex = freeVariableIndices[freeVariableIndex];
        for(var value = 0; value <= maxGuess; value++) {
            currentSolution[variableIndex] = value;
            backtrack(freeVariableIndex + 1, currentSolution, maxGuess);
        }

        currentSolution[variableIndex] = 0;
    }

    public double[] solveForMinimum(int maxGuess) {
        minimumTotalSum = Double.POSITIVE_INFINITY;
        bestSolution    = null;

        backtrack(0, new double[numberOfVariables], maxGuess);

        return bestSolution;
    }
}
