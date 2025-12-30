package io.github.maritims.advent_of_code.common.algebra;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class LinearSystemSolverTest {

    public static Stream<Arguments> toRowEchelonForm() {
        return Stream.of(
                Arguments.of(new double[][]{
                                {1.0, 1.0, 1.0, 6.0},   // x + y + z = 6
                                {3.0, 2.0, 5.0, -4.0},  // 3x + 2y + 5z = -4
                                {2.0, 5.0, -1.0, 27.0}  // 2x + 5y - z = 27
                        },
                        new double[][]{
                                {1.0, 1.0, 1.0, 6.0},
                                {0.0, -1.0, 2.0, -22.0},
                                {0.0, 0.0, 3.0, -51.0}
                        })
        );
    }

    public static Stream<Arguments> solve() {
        return Stream.of(
                Arguments.of(new double[][]{
                        {1.0, 1.0, 1.0, 6.0},           // x + y + z = 6
                        {3.0, 2.0, 5.0, -4.0},          // 3x + 2y + 5z = -4
                        {2.0, 5.0, -1.0, 27.0}          // 2x + 5y - z = 27
                }, new double[]{35.0, -12.0, -17.0}),   // x = 35, y = -12, z = 17
                Arguments.of(new double[][] {
                        {0.0, 2.0, 1.0, 4.0}, // 0x + 2y + z = 4
                        {1.0, 1.0, 2.0, 5.0}, // x + y + z = 5
                        {2.0, 3.0, -1.0, 1.0} // 2x + 3y - z = 1
                }, new double[]{})
        );
    }

    @ParameterizedTest
    @MethodSource
    public void toRowEchelonForm(double[][] matrix, double[][] expectedResult) {
        LinearSystemSolver.toRowEchelonForm(matrix);
        assertArrayEquals(expectedResult, matrix);
    }

    @ParameterizedTest
    @MethodSource
    public void solve(double[][] matrix, double[] expectedResult) {
        var result = LinearSystemSolver.solve(matrix);
        assertArrayEquals(expectedResult, result);
    }
}
