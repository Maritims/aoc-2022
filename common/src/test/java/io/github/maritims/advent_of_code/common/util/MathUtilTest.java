package io.github.maritims.advent_of_code.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class MathUtilTest {

    public static Stream<Arguments> gcd() {
        return Stream.of(
            arguments(12, 6, 6),
            arguments(100, 12, 4)
        );
    }

    public static Stream<Arguments> lcm() {
        return Stream.of(
            arguments(4, 6, 12)
        );
    }

    public static Stream<Arguments> lcmWithMoreThanTwoNumbers() {
        return Stream.of(
            arguments(new Long[] { 8L, 12L, 15L }, 120)
        );
    }

    @ParameterizedTest
    @MethodSource
    void gcd(long a, long b, long expectedResult) {
        Assertions.assertEquals(expectedResult, MathUtil.gcd(a, b));
    }

    @ParameterizedTest
    @MethodSource
    void lcm(long a, long b, long expectedResult) {
        assertEquals(expectedResult, MathUtil.lcm(a, b));
    }

    @ParameterizedTest
    @MethodSource
    void lcmWithMoreThanTwoNumbers(Long[] a, long expectedResult) {
        assertEquals(expectedResult, MathUtil.lcm(a));
    }
}