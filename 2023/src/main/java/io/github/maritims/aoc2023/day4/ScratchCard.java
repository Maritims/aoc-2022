package io.github.maritims.aoc2023.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ScratchCard {
    private static final Pattern numberPattern = Pattern.compile("(\\d+)");

    private final int id;
    private final List<Integer> numbers;
    private final List<Integer> solution;

    public static ScratchCard fromString(String input) {
        var id = Integer.parseInt(input.substring(5, input.indexOf(":")).trim());
        var parts  = input.substring(input.indexOf(":") + 1).split(" \\| ");
        var numbers = numberPattern.matcher(parts[0])
            .results()
            .map(result -> Integer.parseInt(result.group()))
            .collect(Collectors.toCollection(ArrayList::new));
        var solution = numberPattern.matcher(parts[1])
            .results()
            .map(result -> Integer.parseInt(result.group()))
            .collect(Collectors.toCollection(ArrayList::new));
        return new ScratchCard(id, numbers, solution);
    }

    private ScratchCard(int id, List<Integer> numbers, List<Integer> solution) {
        this.id         = id;
        this.numbers    = numbers;
        this.solution   = solution;
    }

    private List<Integer> winningNumbers;

    public List<Integer> getWinningNumbers() {
        if(winningNumbers == null) {
            winningNumbers = numbers.stream()
                .filter(solution::contains)
                .collect(Collectors.toCollection(ArrayList::new));
        }
        return winningNumbers;
    }

    private Integer worth;

    public int getWorth() {
        if (worth == null) {
            worth = getWinningNumbers()
                .stream()
                .reduce(0, (worth, ignored) -> worth == 0 ? 1 : (worth * 2));
        }
        return worth;
    }

    @Override
    public String toString() {
        return "ScratchCard{" +
            "id=" + id +
            ", numbers=" + numbers +
            ", solution=" + solution +
            '}';
    }
}
