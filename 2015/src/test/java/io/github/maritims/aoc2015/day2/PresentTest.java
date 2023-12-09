package io.github.maritims.aoc2015.day2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PresentTest {

    public static Stream<Arguments> getSurfaceArea() {
        return Stream.of(
            arguments(2, 3, 4, 52),
            arguments(1, 1, 10, 42)
        );
    }

    public static Stream<Arguments> getSlack() {
        return Stream.of(
            arguments(2, 3, 4, 6),
            arguments(1, 1, 10, 1)
        );
    }

    public static Stream<Arguments> getRequiredWrappingPaperArea() {
        return Stream.of(
            arguments(2, 3, 4, 58),
            arguments(1, 1, 10, 43)
        );
    }

    public static Stream<Arguments> getSmallestPerimeterOfAnyFace() {
        return Stream.of(
            arguments(2, 3, 4, 10),
            arguments(1, 1, 10, 4)
        );
    }

    public static Stream<Arguments> getVolume() {
        return Stream.of(
            arguments(2, 3, 4, 24),
            arguments(1, 1, 10, 10)
        );
    }

    public static Stream<Arguments> getRequiredRibbonLength() {
        return Stream.of(
            arguments(2, 3, 4, 34),
            arguments(1, 1, 10, 14)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getSurfaceArea(int length, int width, int height, int expectedResult) {
        assertEquals(expectedResult, new Present(length, width, height).getSurfaceArea());
    }

    @ParameterizedTest
    @MethodSource
    void getSlack(int length, int width, int height, int expectedResult) {
        assertEquals(expectedResult, new Present(length, width, height).getSlack());
    }

    @ParameterizedTest
    @MethodSource
    void getRequiredWrappingPaperArea(int length, int width, int height, int expectedResult) {
        assertEquals(expectedResult, new Present(length, width, height).getRequiredWrappingPaperArea());
    }

    @ParameterizedTest
    @MethodSource
    void getSmallestPerimeterOfAnyFace(int length, int width, int height, int expectedResult) {
        assertEquals(expectedResult, new Present(length, width, height).getSmallestPerimeterOfAnyFace());
    }

    @ParameterizedTest
    @MethodSource
    void getVolume(int length, int width, int height, int expectedResult) {
        assertEquals(expectedResult, new Present(length, width, height).getVolume());
    }

    @ParameterizedTest
    @MethodSource
    void getRequiredRibbonLength(int length, int width, int height, int expectedResult) {
        assertEquals(expectedResult, new Present(length, width, height).getRequiredRibbonLength());
    }
}