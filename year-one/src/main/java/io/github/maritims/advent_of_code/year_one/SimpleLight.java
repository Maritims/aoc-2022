package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.StateAction;

import java.util.Objects;

public class SimpleLight implements Light {
    private boolean isOn;

    public SimpleLight() {}

    @Override
    public void turnOn() {
        isOn = true;
    }

    @Override
    public void turnOff() {
        isOn = false;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void toggle() {
        isOn = !isOn;
    }

    @Override
    public void setState(StateAction action) {
        switch (action) {
            case Enable -> turnOn();
            case Disable -> turnOff();
            case Toggle -> toggle();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SimpleLight that = (SimpleLight) o;
        return isOn == that.isOn;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isOn);
    }

    @Override
    public String toString() {
        return "SimpleLight{" +
               "isOn=" + isOn +
               '}';
    }
}
