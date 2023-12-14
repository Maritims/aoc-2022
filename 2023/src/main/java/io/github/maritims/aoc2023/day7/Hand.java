package io.github.maritims.aoc2023.day7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hand implements Comparable<Hand> {
    private final String                cards;
    private final LinkedList<Character> cardValues;
    private final HandType              type;

    public Hand(String cards, LinkedList<Character> cardValues) {
        if (cards.length() != 5) {
            throw new IllegalArgumentException("Invalid number of cards");
        }

        var chars = IntStream.range(0, cards.length())
            .mapToObj(cards::charAt)
            .collect(Collectors.toCollection(ArrayList::new));

        this.cards      = cards;
        this.cardValues = cardValues;
        var groups = chars.stream()
            .collect(Collectors.groupingBy(c -> c));

        if (groups.size() == 1) {
            type = HandType.FiveOfAKind;
        } else if (groups.size() == 2 && groups.values().stream().map(List::size).anyMatch(size -> size == 4)) {
            type = HandType.FourOfAKind;
        } else if (groups.size() == 2 && groups.values().stream().map(List::size).anyMatch(size -> size == 3) && groups.values().stream().map(List::size).anyMatch(size -> size == 2)) {
            type = HandType.FullHouse;
        } else if (groups.size() == 3 && groups.values().stream().map(List::size).anyMatch(size -> size == 3)) {
            type = HandType.ThreeOfAKind;
        } else if (groups.size() == 3 && groups.values().stream().map(List::size).anyMatch(size -> size == 2)) {
            type = HandType.TwoPair;
        } else if (groups.size() == 4) {
            type = HandType.OnePair;
        } else {
            type = HandType.HighCard;
        }
    }

    public String getCards() {
        return cards;
    }

    public HandType getType() {
        return type;
    }

    @Override
    public String toString() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hand hand = (Hand) o;

        if (!Objects.equals(cards, hand.cards)) return false;
        return type == hand.type;
    }

    @Override
    public int hashCode() {
        int result = cards != null ? cards.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Hand o) {
        if (type.ordinal() > o.type.ordinal()) return 1;
        if (type.ordinal() < o.type.ordinal()) return -1;

        for (var i = 0; i < cards.length(); i++) {
            var w1 = cardValues.indexOf(cards.charAt(i)) + 1;
            var w2 = cardValues.indexOf(o.getCards().charAt(i)) + 1;
            if (w1 > w2) return 1;
            if (w1 < w2) return -1;
        }

        return 0;
    }
}