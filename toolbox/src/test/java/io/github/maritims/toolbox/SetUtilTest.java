package io.github.maritims.toolbox;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SetUtilTest {

    public static Stream<Arguments> getPowerSet() {
        return Stream.of(
            arguments(
                Set.of("APPLE", "MANGO", "ORANGE"),
                Set.of(
                    Set.of(),
                    Set.of("APPLE", "ORANGE"),
                    Set.of("APPLE"),
                    Set.of("MANGO"),
                    Set.of("APPLE", "MANGO"),
                    Set.of("MANGO", "ORANGE"),
                    Set.of("APPLE", "MANGO", "ORANGE"),
                    Set.of("ORANGE")
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getPowerSet(Set<String> set, Set<Set<String>> expectedResult) {
        assertEquals(expectedResult, SetUtil.getPowerSet(set));
    }
}