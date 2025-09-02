package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.Instruction;
import io.github.maritims.advent_of_code.common.geometry.Line2D;
import io.github.maritims.advent_of_code.common.StateAction;
import io.github.maritims.advent_of_code.year_one.Day6.InstructionBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {
    public static Stream<Arguments> buildInstructionFromString() {
        return Stream.of(
                Arguments.of("turn on 0,0 through 999,999", new Instruction<>(Line2D.newBuilder().from(0, 0).to(999, 999).build(), StateAction.Enable)),
                Arguments.of("toggle 0,0 through 999,0", new Instruction<>(Line2D.newBuilder().from(0, 0).to(999, 0).build(), StateAction.Toggle)),
                Arguments.of("turn off 499,499 through 500,500", new Instruction<>(Line2D.newBuilder().from(499, 499).to(500, 500).build(), StateAction.Disable))
        );
    }

    @ParameterizedTest
    @MethodSource
    void buildInstructionFromString(String instruction, Instruction<Line2D, StateAction> expectedResult) {
        var result = InstructionBuilder.buildFromString(instruction);
        assertEquals(expectedResult, result);
    }

    @Test
    void solveFirstPart() {
        assertEquals(377891, new Day6().solveFirstPart());
    }

    @Test
    void solveSecondPart() {
        assertEquals(14110788, new Day6().solveSecondPart());
    }
}