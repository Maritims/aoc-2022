package io.github.maritims.aoc2022.day16;

import java.util.Arrays;

public class State {
    private final Integer valve;
    private final int remainingTime;
    private final boolean[] openValves;

    public State(Integer valve, int remainingTime, boolean[] openValves) {
        this.valve = valve;
        this.remainingTime = remainingTime;
        this.openValves = openValves;
    }

    public Integer getValve() {
        return valve;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public boolean[] getOpenValves() {
        return openValves;
    }

    @Override
    public String toString() {
        return "(valve=" + valve + ", remainingTime=" + remainingTime + ", openValves=" + Arrays.toString(openValves) + ")";
    }
}
