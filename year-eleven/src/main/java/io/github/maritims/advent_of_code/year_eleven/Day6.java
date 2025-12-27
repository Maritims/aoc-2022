package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.LongComposition;
import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.arithmetic.ArithmeticOperation;

import java.util.ArrayList;
import java.util.Arrays;

public class Day6 extends PuzzleSolver<Long, Long> {
    @Override
    public Long solveFirstPart() {
        var input = loadInput();
        var operators = Arrays.stream(input.getLast().trim().split("\\s+"))
                .map(operator -> ArithmeticOperation.fromOperator(operator.trim().charAt(0)))
                .toList();
        var results = Arrays.stream(input.getFirst().trim().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
        var grandTotal = 0L;

        for (int i = 1; i < input.size() - 1; i++) {
            var columns = Arrays.stream(input.get(i).trim().split("\\s+"))
                    .mapToLong(Long::parseLong)
                    .toArray();
            for (var j = 0; j < columns.length; j++) {
                results[j] = operators.get(j).evaluate(results[j], columns[j]);

                if (i == input.size() - 2) {
                    grandTotal += results[j];
                }
            }
        }

        return grandTotal;
    }

    @Override
    public Long solveSecondPart() {
        var lines = loadInput();
        var operators = Arrays.stream(lines.removeLast().split("\\s+"))
                .map(operator -> ArithmeticOperation.fromOperator(operator.trim().charAt(0)))
                .toList();
        var lineLength = lines.getLast().length();
        var realColumn = operators.size() - 1;
        var compositions = new ArrayList<LongComposition>();
        var grandTotal = 0L;

        for (var col = lineLength - 1; col >= 0; col--) {
            var builder = LongComposition.newBuilder();
            var isDelimiter = true;

            for (var line : lines) {
                var characterInColumn = line.charAt(col);
                if (line.charAt(col) != ' ') {
                    isDelimiter = false;
                    builder.addPart(Character.getNumericValue(characterInColumn));
                }
            }

            // Always add a composition if it's not a delimiter.
            if(!isDelimiter) {
                compositions.add(builder.build());
            }

            // Flush to grandTotal when we encounter a delimiter or the first column, and we have actually have compositions to flush (which should be always).
            if ((isDelimiter && !compositions.isEmpty()) || (col == 0 && !compositions.isEmpty())) {
                final var operator = operators.get(realColumn);
                var evaluated = compositions.stream()
                        .map(LongComposition::toLong)
                        .reduce(operator::evaluate)
                        .orElseThrow();
                grandTotal = Math.addExact(grandTotal, evaluated);
                compositions = new ArrayList<>();
                realColumn--;
            }
        }

        return grandTotal;
    }
}
