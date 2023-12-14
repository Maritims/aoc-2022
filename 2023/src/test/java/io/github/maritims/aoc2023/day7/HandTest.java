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
            arguments("AAAAA", HandType.FiveOfAKind),
            arguments("AA8AA", HandType.FourOfAKind),
            arguments("23332", HandType.FullHouse),
            arguments("TTT98", HandType.ThreeOfAKind),
            arguments("23432", HandType.TwoPair),
            arguments("A23A4", HandType.OnePair),
            arguments("23456", HandType.HighCard)
        );
    }

    @ParameterizedTest
    @MethodSource
    void getHandType(String hand, HandType expectedResult) {
        assertEquals(expectedResult, new Hand(hand, null).getType());
    }
}