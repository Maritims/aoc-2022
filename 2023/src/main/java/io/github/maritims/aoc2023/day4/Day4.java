package io.github.maritims.aoc2023.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {
    private final List<ScratchCard> scratchCards;

    public Day4(List<String> lines) {
        scratchCards = lines.stream()
            .map(ScratchCard::fromString)
            .collect(Collectors.toCollection(ArrayList::new));
    }
    public Day4(String fileName) throws IOException {
        this(Files.readAllLines(Paths.get("src", "main", "resources", fileName)));
    }

    public int solvePartOne() {
        return scratchCards.stream()
            .mapToInt(ScratchCard::getWorth)
            .sum();
    }

    protected int getTotalCards(int i, int winningNumbers) {
        var subListBeginning = i + 1;
        var subListEnd = Math.min(subListBeginning + winningNumbers, scratchCards.size());

        var totalCards = 0;
        for(int j = subListBeginning; j < subListEnd; j++) {
            var prizeCard = scratchCards.get(j);
            totalCards++;
            totalCards += getTotalCards(j, prizeCard.getWinningNumbers().size());
        }

        return totalCards;
    }

    public int solvePartTwo() {
        var totalCards = 0;

        for(int i = 0; i < scratchCards.size(); i++) {
            totalCards++;
            totalCards += getTotalCards(i, scratchCards.get(i).getWinningNumbers().size());
        }

        return totalCards;
    }
}
