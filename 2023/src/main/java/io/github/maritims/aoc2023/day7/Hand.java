package io.github.maritims.aoc2023.day7;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public Hand(String hand) {
        this(
            IntStream.range(0, hand.length())
                .mapToObj(hand::charAt)
                .map(Card::new)
                .toList()
        );
    }

    public HandType getHandType() {
        var groups = cards.stream()
            .collect(Collectors.groupingBy(Card::getValue));

        if (groups.size() == 1) {
            return HandType.FiveOfAKind;
        } else if (groups.size() == 2 && groups.values().stream().anyMatch(cards -> cards.size() == 4)) {
            return HandType.FourOfAKind;
        } else if (groups.size() == 2 && groups.values().stream().anyMatch(cards -> cards.size() == 3) && groups.values().stream().anyMatch(cards -> cards.size() == 2)) {
            return HandType.FullHouse;
        } else if (groups.size() == 3 && groups.values().stream().anyMatch(cards -> cards.size() == 3)) {
            return HandType.ThreeOfAKind;
        } else if (groups.size() == 3 && groups.values().stream().anyMatch(cards -> cards.size() == 2)) {
            return HandType.TwoPair;
        } else if (groups.size() == 4) {
            return HandType.OnePair;
        } else {
            return HandType.HighCard;
        }
    }

    public Integer getWorth() {
        return cards.stream()
            .map(Card::getWorth)
            .reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        return cards.stream()
            .map(Card::getValue)
            .map(String::valueOf)
            .collect(Collectors.joining());
    }
}
