package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.PuzzleSolver;
import io.github.maritims.advent_of_code.common.util.MathUtil;

import java.util.Arrays;

public class Day6 extends PuzzleSolver<Long, Long> {
    @Override
    public Long solveFirstPart() {
        var input = loadInput();
        var operators = Arrays.stream(input.get(input.size() - 1).trim().split("\\s+"))
                .map(operator -> MathUtil.resolveFunctionFromOperator(operator.trim().charAt(0)))
                .toList();
        var results = Arrays.stream(input.get(0).trim().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
        var grandTotal = 0L;

        for (int i = 1; i < input.size() - 1; i++) {
            var columns = Arrays.stream(input.get(i).trim().split("\\s+"))
                    .mapToLong(Long::parseLong)
                    .toArray();
            for (var j = 0; j < columns.length; j++) {
                results[j] = operators.get(j).apply(results[j], columns[j]).longValue();

                if(i == input.size() - 2) {
                    grandTotal += results[j];
                }
            }
        }

        return grandTotal;
    }

    @Override
    public Long solveSecondPart() {
        var input = loadInput();
        var operators = Arrays.stream(input.get(input.size() - 1).trim().split("\\s+"))
                .map(operator -> MathUtil.resolveFunctionFromOperator(operator.trim().charAt(0)))
                .toList();

        for(var i = 1; i < input.size() - 1; i++) {
            System.out.println(input.get(i));
        }
        return 0L;
    }
}
