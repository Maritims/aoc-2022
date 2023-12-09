package io.github.maritims.aoc2015.day1;

import java.util.Objects;

public class Result {
    private final Integer enteredBasementAtPosition;
    private final int     finalFloor;

    public Result(Integer enteredBasementAtPosition, int finalFloor) {
        this.enteredBasementAtPosition = enteredBasementAtPosition;
        this.finalFloor                = finalFloor;
    }

    public Integer getEnteredBasementAtPosition() {
        return enteredBasementAtPosition;
    }

    public int getFinalFloor() {
        return finalFloor;
    }

    @Override
    public String toString() {
        return "Result{" +
            "enteredBasementAtPosition=" + enteredBasementAtPosition +
            ", finalFloor=" + finalFloor +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (finalFloor != result.finalFloor) return false;
        return Objects.equals(enteredBasementAtPosition, result.enteredBasementAtPosition);
    }

    @Override
    public int hashCode() {
        int result = enteredBasementAtPosition != null ? enteredBasementAtPosition.hashCode() : 0;
        result = 31 * result + finalFloor;
        return result;
    }
}