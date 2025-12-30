package io.github.maritims.advent_of_code.year_eleven;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FactoryMachineTest {

    public static Stream<Arguments> pressButton_shouldSetTheIndicatorLights_whenConfigurationModeIsIndicatorLights() {
        return Stream.of(
                Arguments.of(new boolean[]{false, false, false, false}, new int[][]{{3}, {1, 3}, {2}, {2, 3}, {0, 2}, {0, 1}}, new int[]{0, 1, 2}, new boolean[]{false, true, true, false}, new int[4], new int[]{3, 5, 4, 7}),
                Arguments.of(new boolean[]{false, false, false, false, false}, new int[][]{{0, 2, 3, 4}, {2, 3}, {0, 4}, {0, 1, 2}, {1, 2, 3, 4}}, new int[]{2, 3, 4}, new boolean[]{false, false, false, true, false}, new int[5], new int[]{7, 5, 12, 7, 2}),
                Arguments.of(new boolean[]{false, false, false, false, false, false}, new int[][]{{0, 1, 2, 3, 4}, {0, 3, 4}, {0, 1, 2, 4, 5}, {1, 2}}, new int[]{1, 2}, new boolean[]{false, true, true, true, false, true}, new int[6], new int[]{10, 11, 11, 5, 10, 5})
        );
    }

    public static Stream<Arguments> pressButton_shouldSetTheJoltages_whenConfigurationModeIsJoltages() {
        return Stream.of(
                Arguments.of(new boolean[]{false, false, false, false}, new int[][]{{3}, {1, 3}, {2}, {2, 3}, {0, 2}, {0, 1}}, new int[]{0, 1, 1, 1, 3, 3, 3, 4, 5, 5}, new boolean[]{false, true, true, false}, new int[4], new int[]{3, 5, 4, 7}),
                Arguments.of(new boolean[]{false, false, false, false, false}, new int[][]{{0, 2, 3, 4}, {2, 3}, {0, 4}, {0, 1, 2}, {1, 2, 3, 4}}, new int[]{0, 0, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3}, new boolean[]{false, false, false, true, false}, new int[5], new int[]{7, 5, 12, 7, 2}),
                Arguments.of(new boolean[]{false, false, false, false, false, false}, new int[][]{{0, 1, 2, 3, 4}, {0, 3, 4}, {0, 1, 2, 4, 5}, {1, 2}}, new int[]{0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 3}, new boolean[]{false, true, true, true, false, true}, new int[6], new int[]{10, 11, 11, 5, 10, 5})
        );
    }

    public static Stream<Arguments> parseFactoryMachine() {
        return Stream.of(
                Arguments.of("[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}"),
                Arguments.of("[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}"),
                Arguments.of("[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}")
        );
    }

    @ParameterizedTest
    @MethodSource
    void pressButton_shouldSetTheIndicatorLights_whenConfigurationModeIsIndicatorLights(boolean[] indicatorLights, int[][] buttonWiringSchematics, int[] buttonPresses, boolean[] indicatorLightDiagram, int[] joltages, int[] joltageRequirements) {
        // arrange
        var sut = new FactoryMachine(indicatorLights, buttonWiringSchematics, indicatorLightDiagram, joltages, joltageRequirements);

        // act
        sut.pressButtons(buttonPresses);
        var result = sut.getIndicatorLights();

        // assert
        assertArrayEquals(indicatorLightDiagram, result, () -> "\nExpected result: " + Arrays.toString(indicatorLightDiagram) + "\nActual result: " + Arrays.toString(result));
    }

    @ParameterizedTest
    @MethodSource
    void pressButton_shouldSetTheJoltages_whenConfigurationModeIsJoltages(boolean[] indicatorLights, int[][] buttonWiringSchematics, int[] buttonPresses, boolean[] indicatorLightDiagram, int[] joltages, int[] joltageRequirements) {
        var sut = new FactoryMachine(indicatorLights, buttonWiringSchematics, indicatorLightDiagram, joltages, joltageRequirements);
        sut.pullLever();
        sut.pressButtons(buttonPresses);
        var result = sut.getJoltages();

        assertArrayEquals(joltageRequirements, result, () -> "\nExpected result: " + Arrays.toString(joltageRequirements) + "\nActual result: " + Arrays.toString(result));
    }

    @ParameterizedTest
    @MethodSource
    void parseFactoryMachine(String str) {
        FactoryMachine.parseFactoryMachine(str);
    }
}