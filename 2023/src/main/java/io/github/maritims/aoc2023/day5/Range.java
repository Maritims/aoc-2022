package io.github.maritims.aoc2023.day5;

public class Range {
    private final Double destinationStart;
    private final Double destinationEnd;
    private final Double sourceStart;
    private final Double sourceEnd;
    private final Double length;
    private final Double offset;

    public Range(Double destinationStart, Double sourceStart, Double length) {
        this.destinationStart = destinationStart;
        this.destinationEnd   = destinationStart == null ? null : destinationStart + length - 1;
        this.sourceStart      = sourceStart;
        this.sourceEnd        = sourceStart + length - 1;
        this.length           = length;
        this.offset           = destinationStart == null ? null : destinationStart - sourceStart;
    }

    public Double getDestinationStart() {
        return destinationStart;
    }

    public Double getDestinationEnd() {
        return destinationEnd;
    }

    public Double getSourceStart() {
        return sourceStart;
    }

    public Double getSourceEnd() {
        return sourceEnd;
    }

    public Double getLength() {
        return length;
    }

    public Double getOffset() {
        return offset;
    }

    public boolean isInRange(Double value) {
        return value >= sourceStart && value <= sourceEnd;
    }

    @Override
    public String toString() {
        return "Range{" +
            "destinationStart=" + destinationStart +
            ", destinationEnd=" + destinationEnd +
            ", sourceStart=" + sourceStart +
            ", sourceEnd=" + sourceEnd +
            ", length=" + length +
            ", offset=" + offset +
            '}';
    }
}