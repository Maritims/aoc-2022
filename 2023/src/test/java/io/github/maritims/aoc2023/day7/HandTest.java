package io.github.maritims.aoc2023.day7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class HandTest {

    public static Stream<Arguments> getHandType() {
        return Stream.of(
            arguments("23332", false, HandType.FullHouse),
            arguments("TTT98", false, HandType.ThreeOfAKind),
            arguments("23432", false, HandType.TwoPair),
            arguments("A23A4", false, HandType.OnePair),
            arguments("23456", false, HandType.HighCard),

            arguments("AAAAA", true, HandType.FiveOfAKind),
            arguments("AAAAJ", true, HandType.FiveOfAKind),
            arguments("AAAJJ", true, HandType.FiveOfAKind),
            arguments("AAJJJ", true, HandType.FiveOfAKind),
            arguments("AJJJJ", true, HandType.FiveOfAKind),
            arguments("JJJJJ", true, HandType.FiveOfAKind),

            arguments("AAABJ", true, HandType.FourOfAKind),
            arguments("AABJJ", true, HandType.FourOfAKind),
            arguments("ABJJJ", true, HandType.FourOfAKind),
            arguments("AJJJJ", true, HandType.FiveOfAKind),

            arguments("AACBJ", true, HandType.ThreeOfAKind),
            arguments("ACBDJ", true, HandType.OnePair),

            arguments("32T3K", true, HandType.OnePair),
            arguments("T55J5", true, HandType.FourOfAKind),
            arguments("KK677", true, HandType.TwoPair),
            arguments("KTJJT", true, HandType.FourOfAKind),
            arguments("QQQJA", true, HandType.FourOfAKind)
        );
    }

    public static Stream<Arguments> compareTo() {
        return Stream.of(
            arguments("32T3K", "32T3K", false, 0),
            arguments("32T3K", "T55J5", false, -1),
            arguments("32T3K", "KK677", false, -1),
            arguments("32T3K", "KTJJT", false, -1),
            arguments("32T3K", "QQQJA", false, -1),

            arguments("T55J5", "32T3K", false, 1),
            arguments("T55J5", "T55J5", false, 0),
            arguments("T55J5", "KK677", false, -1),
            arguments("T55J5", "KTJJT", false, -1),
            arguments("T55J5", "QQQJA", false, -1),

            arguments("KK677", "32T3K", false, 1),
            arguments("KK677", "T55J5", false, 1),
            arguments("KK677", "KK677", false, 0),
            arguments("KK677", "KTJJT", false, 1),
            arguments("KK677", "QQQJA", false, -1),

            arguments("T55J5", "T55J5", true, 0),
            arguments("T55J5", "32T3K", true, 1),
            arguments("T55J5", "KK677", true, -1),
            arguments("T55J5", "KTJJT", true, -1),
            arguments("T55J5", "QQQJA", true, -1)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getHandType(String hand, boolean isJokerEnabled, HandType expectedResult) {
        var instance = new Hand(hand, isJokerEnabled);
        assertEquals(expectedResult, instance.getType());
    }

    @ParameterizedTest
    @MethodSource
    void compareTo(String o1, String o2, boolean isJokerEnabled, int expectedResult) {
        // arrange
        var hand1 = new Hand(o1, isJokerEnabled);
        var hand2 = new Hand(o2, isJokerEnabled);

        // act
        var result = hand1.compareTo(hand2);

        // assert
        assertEquals(expectedResult, result);
    }
}