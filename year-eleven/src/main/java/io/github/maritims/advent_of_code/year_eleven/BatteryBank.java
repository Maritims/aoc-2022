package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static io.github.maritims.advent_of_code.common.util.ArgumentExceptionUtil.throwIfNullOrEmpty;

public class BatteryBank {
    private final List<Battery> batteries;

    public BatteryBank(List<Battery> batteries) {
        throwIfNullOrEmpty(batteries, "batteries");
        this.batteries = batteries;
    }

    public static BatteryBank ofJoltageRatings(String joltageRatings) {
        var batteries = Arrays.stream(StringUtils.toIntArray(joltageRatings))
                .mapToObj(i -> new Battery(i, false))
                .toList();
        return new BatteryBank(batteries);
    }

    public int currentJoltage() {
        return batteries.stream()
                .filter(Battery::poweredOn)
                .mapToInt(Battery::joltage)
                .sum();
    }

    public int maximumJoltage() {
        return batteries.stream()
                .sorted(Comparator.comparing(Battery::joltage))
                .skip(batteries.size() - 2)
                .sorted(Comparator.comparing(Battery::joltage).reversed())
                .mapToInt(Battery::joltage)
                .reduce(0, (first, last) -> first * 10 + last);
    }
}
