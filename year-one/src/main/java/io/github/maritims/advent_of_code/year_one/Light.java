package io.github.maritims.advent_of_code.year_one;

import io.github.maritims.advent_of_code.common.StateControllable;
import io.github.maritims.advent_of_code.common.Toggleable;

public interface Light extends StateControllable,
                               Toggleable {
    void turnOn();

    void turnOff();

    boolean isOn();
}
