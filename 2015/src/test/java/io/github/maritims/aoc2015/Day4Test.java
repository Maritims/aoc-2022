package io.github.maritims.aoc2015;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    public static Stream<Arguments> solve() {
        return Stream.of(
            Arguments.arguments("abcdef", "00000", 609043),
            Arguments.arguments("pqrstuv", "00000", 1048970),
            Arguments.arguments("yzbqklnj", "00000", 282749),
            Arguments.arguments("yzbqklnj", "000000", 9962624)
        );
    }

    @ParameterizedTest
    @MethodSource
    void solve(String secretKey, String leadingZeros, int expectedResult) throws NoSuchAlgorithmException {
        assertEquals(expectedResult, new Day4().solve(secretKey, leadingZeros));
    }
}