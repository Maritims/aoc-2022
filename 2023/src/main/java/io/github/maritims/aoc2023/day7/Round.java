package io.github.maritims.aoc2023.day7;

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

    @Override
    public String toString() {
        return hand + " " + bid;
    }
}