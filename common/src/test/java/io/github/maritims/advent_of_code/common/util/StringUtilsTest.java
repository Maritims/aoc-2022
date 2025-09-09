package io.github.maritims.advent_of_code.common.util;

import org.junit.jupiter.api.Assertions;
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

    public static Stream<Arguments> unescapeString() {
        //noinspection UnnecessaryStringEscape
        return Stream.of(
                Arguments.of("""
                                     ""
                                     """.trim(), ""),
                Arguments.of("""
                                     "abc"
                                     """.trim(), "abc"),
                Arguments.of("""
                                     "aaa\\\"aaa"
                                     """.trim(), "aaa\"aaa"),
                Arguments.of("""
                                     \\x27
                                     """.trim(), "'")
        );
    }

    public static Stream<Arguments> escapeString() {
        return Stream.of(
                Arguments.of("\"\"", "\"\\\"\\\"\""),
                Arguments.of("\"abc\"", "\"\\\"abc\\\"\""),
                Arguments.of("\"aaa\\\"aaa", "\"\\\"aaa\\\\\\\"aaa\""),
                Arguments.of("\"\\x27\"", "\"\\\"\\\\x27\\\"\"")
        );
    }

    public static Stream<Arguments> lookAndSay() {
        return Stream.of(
                Arguments.of("1", "11"),
                Arguments.of("11", "21"),
                Arguments.of("21", "1211"),
                Arguments.of("1211", "111221"),
                Arguments.of("111221", "312211"),
                Arguments.of("4444333221", "44332211")
        );
    }

    public static Stream<Arguments> hasIncreasingStraightSequence() {
        return Stream.of(
                Arguments.of("hijklmmn", true),
                Arguments.of("abbceffg", false),
                Arguments.of("abc", true),
                Arguments.of("bcd", true),
                Arguments.of("cde", true),
                Arguments.of("xyz", true),
                Arguments.of("abd", false)
        );
    }

    public static Stream<Arguments> hasNoneOfTheseLetters() {
        return Stream.of(
                Arguments.of("hijklmn", false)
        );
    }

    public static Stream<Arguments> hasNonOverlappingPairs() {
        return Stream.of(
                Arguments.of("aaaa", false),
                Arguments.of("aabb", true)
        );
    }

    public static Stream<Arguments> incrementString() {
        return Stream.of(
                Arguments.of("xx", "xy"),
                Arguments.of("xy", "xz"),
                Arguments.of("xz", "ya"),
                Arguments.of("ya", "yb")
        );
    }

    @ParameterizedTest
    @MethodSource
    void hasRepeatingLetterWithSingleDelimiter(String str, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, StringUtils.hasRepeatingLetterWithSingleDelimiter(str));
    }

    @ParameterizedTest
    @MethodSource
    void hasRepeatedPairWithAnythingInBetween(String str, boolean expectedResult) {
        assertEquals(expectedResult, StringUtils.hasRepeatedPairWithAnythingInBetween(str));
    }

    @ParameterizedTest
    @MethodSource
    void unescapeString(String stringLiteral, String expectedResult) {
        assertEquals(expectedResult, StringUtils.unescapeString(stringLiteral));
    }

    @ParameterizedTest
    @MethodSource
    void escapeString(String str, String expectedResult) {
        assertEquals(expectedResult, StringUtils.escapeString(str));
    }

    @ParameterizedTest
    @MethodSource
    void lookAndSay(String input, String expectedResult) {
        assertEquals(expectedResult, StringUtils.lookAndSay(input));
    }

    @ParameterizedTest
    @MethodSource
    void hasIncreasingStraightSequence(String input, boolean expectedResult) {
        assertEquals(expectedResult, StringUtils.hasIncreasingStraightSequence(input));
    }

    @ParameterizedTest
    @MethodSource
    void hasNoneOfTheseLetters(String input, boolean expectedResult) {
        assertEquals(expectedResult, StringUtils.hasNoneOfTheseLetters(input, 'i', 'o', 'l'));
    }

    @ParameterizedTest
    @MethodSource
    void hasNonOverlappingPairs(String input, boolean expectedResult) {
        assertEquals(expectedResult, StringUtils.hasNonOverlappingPairs(input));
    }

    @ParameterizedTest
    @MethodSource
    void incrementString(String str, String expectedResult) {
        assertEquals(expectedResult, StringUtils.incrementString(str));
    }
}