package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.util.StringUtils;

import java.util.function.Function;

public class Day8 extends PuzzleSolver<Integer, Integer> {
    record Lengths(int originalLength, int convertedLength) {
        static Lengths of(String line, Function<String, String> converter) {
            var convertedLine = converter.apply(line);
            var codeLength    = line.length();
            var trueLength    = convertedLine.length();
            return new Lengths(codeLength, trueLength);
        }
    }

    @Override
    public Integer solveFirstPart() {
        return loadInput()
                .stream()
                .map(line -> Lengths.of(line, StringUtils::unescapeString))
                .mapToInt(lengths -> lengths.originalLength() - lengths.convertedLength())
                .sum();
    }

    @Override
    public Integer solveSecondPart() {
        return loadInput()
                .stream()
                .map(line -> Lengths.of(line, StringUtils::escapeString))
                .mapToInt(lengths -> lengths.convertedLength() - lengths.originalLength())
                .sum();
    }
}
