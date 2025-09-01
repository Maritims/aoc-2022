package io.github.maritims.advent_of_code.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    public static Stream<Arguments> hasRepeatingLetterWithSingleDelimiter() {
        return Stream.of(
                Arguments.of("qjhvhtzxzqqjkmpb", true),
                Arguments.of("xxyxx", true),
                Arguments.of("uurcxstgmygtbstg", false),
                Arguments.of("ieodomkazucvgmuy", true)
        );
    }

    public static Stream<Arguments> hasRepeatedPairWithAnythingInBetween() {
        return Stream.of(
                Arguments.of("qjhvhtzxzqqjkmpb", true),
                Arguments.of("xxyxx", true),
                Arguments.of("uurcxstgmygtbstg", true),
                Arguments.of("ieodomkazucvgmuy", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void hasRepeatingLetterWithSingleDelimiter(String str, boolean expectedResult) {
        assertEquals(expectedResult, StringUtils.hasRepeatingLetterWithSingleDelimiter(str));
    }

    @ParameterizedTest
    @MethodSource
    void hasRepeatedPairWithAnythingInBetween(String str, boolean expectedResult) {
        assertEquals(expectedResult, StringUtils.hasRepeatedPairWithAnythingInBetween(str));
    }
}