package io.github.maritims.aoc2023.day5;

public class Range {
    private final long destinationStart;
    private final long destinationEnd;
    private final long sourceStart;
    private final long sourceEnd;
    private final long length;
    private final long offset;

    public Range(long destinationStart, long sourceStart, long length) {
        this.destinationStart = destinationStart;
        this.destinationEnd = destinationStart + length - 1;
        this.sourceStart    = sourceStart;
        this.sourceEnd      = sourceStart + length - 1;
        this.length = length;
        this.offset         = destinationStart - sourceStart;
    }

    public long getDestinationStart() {
        return destinationStart;
    }

    public long getDestinationEnd() {
        return destinationEnd;
    }

    public long getSourceStart() {
        return sourceStart;
    }

    public long getSourceEnd() {
        return sourceEnd;
    }

    public long getLength() {
        return length;
    }

    public long getOffset() {
        return offset;
    }

    public boolean isInRange(long value) {
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