package io.github.maritims.aoc2023.day7;

import java.util.Objects;

public class Round {
    private final Hand hand;
    private final int  bid;

    public Round(Hand hand, int bid) {
        this.hand = hand;
        this.bid  = bid;
    }

    public Hand getHand() {
        return hand;
    }

    public int getBid() {
        return bid;
    }

    @Override
    public String toString() {
        return hand.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Round round = (Round) o;

        if (bid != round.bid) return false;
        return Objects.equals(hand, round.hand);
    }

    @Override
    public int hashCode() {
        int result = hand != null ? hand.hashCode() : 0;
        result = 31 * result + bid;
        return result;
    }
}