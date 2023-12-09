package io.github.maritims.aoc2023.day2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static io.github.maritims.aoc2023.day2.Color.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    public static Stream<Arguments> getMinimumCubesForPlayability() {
        return Stream.of(
            Arguments.arguments("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", Map.of(Red, 4, Green, 2, Blue, 6), 48),
            Arguments.arguments("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", Map.of(Red, 1, Green, 3, Blue, 4), 12),
            Arguments.arguments("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", Map.of(Red, 20, Green, 13, Blue, 6), 1560),
            Arguments.arguments("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red", Map.of(Red, 14, Green, 3, Blue, 15), 630),
            Arguments.arguments("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green", Map.of(Red, 6, Green, 3, Blue, 2), 36)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getMinimumCubesForPlayability(String gameInput, Map<Color, Integer> expectedCubes, int expectedPower) {
        // arrange
        Game game = new Game(gameInput, Map.of(Red, 0, Green, 0, Blue, 0));

        // act
        Map<Color, Integer> cubes = game.getMinimumCubesForPlayability();
        int                 power = game.getPowerOfMinimumCubesForPlayability();

        // assert
        assertEquals(expectedCubes, cubes);
        assertEquals(expectedPower, power);
    }
}