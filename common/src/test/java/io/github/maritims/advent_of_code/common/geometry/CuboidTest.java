package io.github.maritims.advent_of_code.common.geometry;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CuboidTest {

    public static Stream<Arguments> getSurfaceArea() {
        return Stream.of(
            arguments(2, 3, 4, 52),
            arguments(1, 1, 10, 42)
        );
    }

    public static Stream<Arguments> getVolume() {
        return Stream.of(
            arguments(2, 3, 4, 24),
            arguments(1, 1, 10, 10)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getSurfaceArea(int length, int width, int height, int expectedResult) {
        assertEquals(expectedResult, new Cuboid(length, width, height).getSurfaceArea());
    }

    @ParameterizedTest
    @MethodSource
    void getVolume(int length, int width, int height, int expectedResult) {
        assertEquals(expectedResult, new Cuboid(length, width, height).getVolume());
    }
}