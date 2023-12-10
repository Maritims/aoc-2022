package io.github.maritims.aoc2023.day5;

public class Piece {
    private final double lowerBoundary;
    private final double upperBoundary;
    private final double shift;

    public Piece(double lowerBoundary, double upperBoundary, double shift) {
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
        this.shift         = shift;
    }

    public boolean isApplicable(double x) {
        return lowerBoundary <= x && x <= upperBoundary;
    }

    public Double evaluate(double x) {
        return x + shift;
    }

    @Override
    public String toString() {
        return "x + " + shift + "   " + lowerBoundary + " <= x <= " + upperBoundary;
    }
}
