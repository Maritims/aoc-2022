package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.StateAction;

public class DimmerLight implements DimmableLight {
    private int brightness = 0;

    @Override
    public int getBrightness() {
        return brightness;
    }

    @Override
    public void turnOn() {
        brightness++;
    }

    @Override
    public void turnOff() {
        if (brightness > 0) {
            brightness--;
        }
    }

    @Override
    public boolean isOn() {
        return brightness > 0;
    }

    @Override
    public void toggle() {
        brightness += 2;
    }

    @Override
    public void setState(StateAction action) {
        switch (action) {
            case Enable -> turnOn();
            case Disable -> turnOff();
            case Toggle -> toggle();
        }
    }
}
