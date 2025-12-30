package io.github.maritims.advent_of_code.year_eleven;

import io.github.maritims.advent_of_code.common.algebra.LinearSystemSolver;
import io.github.maritims.advent_of_code.common.algebra.SimpleILPSolver;
import io.github.maritims.advent_of_code.common.graph.BreadthFirstSearch;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class FactoryMachine {
    public enum ConfigurationMode {
        IndicatorLights,
        Joltages
    }

    private static final Pattern   PATTERN = Pattern.compile("\\[([.#]+)]|\\(([\\d,]+)\\)|\\{([\\d,]+)}");
    private final        boolean[] indicatorLights;
    private final        int[][]   buttonWiringSchematics;
    private final        boolean[] indicatorLightDiagram;
    private final        int[]     joltages;
    private final        int[]     joltageRequirementDiagram;

    private ConfigurationMode configurationMode = ConfigurationMode.IndicatorLights;

    public FactoryMachine(boolean[] indicatorLights, int[][] buttonWiringSchematics, boolean[] indicatorLightDiagram, int[] joltages, int[] joltageRequirementDiagram) {
        if (indicatorLights == null || indicatorLights.length == 0) {
            throw new IllegalArgumentException("indicatorLights cannot be null or empty");
        }
        if (buttonWiringSchematics == null || buttonWiringSchematics.length == 0) {
            throw new IllegalArgumentException("buttonWiringSchematics cannot be null or empty");
        }
        if (indicatorLightDiagram == null || indicatorLightDiagram.length == 0 || indicatorLightDiagram.length != indicatorLights.length) {
            throw new IllegalArgumentException("indicatorLightDiagram cannot be null, empty or have a length different from indicatorLights.length");
        }
        if (joltages == null || joltages.length == 0) {
            throw new IllegalArgumentException("joltages cannot be null or empty");
        }
        if (joltageRequirementDiagram == null || joltageRequirementDiagram.length == 0 || joltageRequirementDiagram.length != joltages.length) {
            throw new IllegalArgumentException("joltageRequirements cannot be null, empty or have a length different from joltages.length");
        }

        this.indicatorLights           = indicatorLights;
        this.buttonWiringSchematics    = buttonWiringSchematics;
        this.indicatorLightDiagram     = indicatorLightDiagram;
        this.joltages                  = joltages;
        this.joltageRequirementDiagram = joltageRequirementDiagram;
    }

    public static FactoryMachine parseFactoryMachine(String str) {
        if (str == null || str.isBlank()) {
            throw new IllegalArgumentException("str cannot be null or blank");
        }

        boolean[] indicatorLights        = null;
        boolean[] indicatorLightDiagram  = null;
        var       buttonWiringSchematics = new ArrayList<List<Integer>>();
        int[]     joltages               = null;
        int[]     joltageRequirements    = null;

        var matcher = PATTERN.matcher(str);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                var chars = matcher.group(1).toCharArray();
                indicatorLights       = new boolean[chars.length];
                indicatorLightDiagram = new boolean[chars.length];

                for (var i = 0; i < chars.length; i++) {
                    if (chars[i] == '#') {
                        indicatorLightDiagram[i] = true;
                    } else if (chars[i] == '.') {
                        indicatorLightDiagram[i] = false;
                    } else {
                        throw new IllegalStateException("illegal character " + chars[i] + " in capture group");
                    }
                }
            } else if (matcher.group(2) != null) {
                var digits = Arrays.stream(matcher.group(2).split(",")).map(Integer::parseInt).toList();
                buttonWiringSchematics.add(digits);
            } else if (matcher.group(3) != null) {
                joltageRequirements = Arrays.stream(matcher.group(3).split(",")).mapToInt(Integer::parseInt).toArray();
                joltages            = new int[joltageRequirements.length];
            }
        }

        Objects.requireNonNull(indicatorLights, "indicatorLights was not initialised");
        Objects.requireNonNull(indicatorLightDiagram, "indicatorLightDiagram was not initialised");
        Objects.requireNonNull(buttonWiringSchematics, "buttonWiringSchematics was not initialised");
        Objects.requireNonNull(joltageRequirements, "joltageRequirements was not initialised");

        return new FactoryMachine(
                indicatorLights,
                buttonWiringSchematics.stream()
                        .map(list -> list.stream().mapToInt(Integer::intValue).toArray())
                        .toArray(int[][]::new),
                indicatorLightDiagram,
                joltages,
                joltageRequirements
        );
    }

    public boolean[] getIndicatorLights() {
        return indicatorLights.clone();
    }

    public int[] getJoltages() {
        return joltages.clone();
    }

    private BitSet toBitSet(boolean[] array) {
        var bits = new BitSet(array.length);
        IntStream.range(0, array.length).filter(i -> array[i]).forEach(bits::set);
        return bits;
    }

    /**
     * Power on the machine by using {@link BreadthFirstSearch} to determine the lowest number of button presses required to configure all the indicator lights according to the indicator light diagram.
     *
     * @return The number of button presses.
     */
    public int powerOn() {
        var target = toBitSet(indicatorLightDiagram);
        var start  = toBitSet(indicatorLights);
        var effects = Arrays.stream(buttonWiringSchematics)
                .map(indices -> {
                    var bitSet = new BitSet(indicatorLights.length);
                    Arrays.stream(indices).forEach(bitSet::set);
                    return bitSet;
                })
                .toList();

        var bfs = new BreadthFirstSearch<BitSet>();
        return bfs.findShortestPath(
                start,
                state -> state.equals(target),
                state -> effects.stream()
                        .map(effect -> {
                            var next = (BitSet) state.clone();
                            next.xor(effect);
                            return next;
                        })
                        .toList());
    }

    public int tuneJoltageCounters() {
        var rows   = joltageRequirementDiagram.length;
        var cols   = buttonWiringSchematics.length;
        var matrix = new double[rows][cols + 1]; // The last column is the "solution" column.

        // 1. Fill matrix
        for (var button = 0; button < cols; button++) {
            for (var counterIndex : buttonWiringSchematics[button]) {
                matrix[counterIndex][button] = 1.0;
            }
        }

        for (var row = 0; row < rows; row++) {
            matrix[row][cols] = (double) joltageRequirementDiagram[row] - joltages[row];
        }

        try {
            LinearSystemSolver.toReducedRowEchelonForm(matrix, cols);
            var pressesPerButton = new SimpleILPSolver(matrix, cols).solveForMinimum(180);
            var totalPresses     = 0.0;
            for (var buttonPress : pressesPerButton) {
                // We can't click a button partially, so we round it to the closest integer.
                var rounded = Math.round(buttonPress);

                // A button press in negative form or with decimals is not valid.
                if (buttonPress < -1e-9) {
                    // Skip negative numbers.
                    continue;
                }

                totalPresses += rounded;
            }

            return (int) totalPresses;
        } catch (Exception e) {
            throw new IllegalStateException("The matrix is singular or unsolvable", e);
        }
    }

    /**
     * Press the desired button. The outcome depends on the factory machine's current configuration mode.
     *
     * @param button The button you'd like to press.
     */
    public void pressButton(int button) {
        if (button >= buttonWiringSchematics.length || button < 0) {
            throw new IllegalArgumentException("button " + button + " is not within the bounds of the button wiring schematics: " + Arrays.deepToString(buttonWiringSchematics));
        }

        switch (configurationMode) {
            case ConfigurationMode.IndicatorLights -> Arrays.stream(buttonWiringSchematics[button]).forEach(lightIndex -> indicatorLights[lightIndex] = !indicatorLights[lightIndex]);
            case ConfigurationMode.Joltages -> Arrays.stream(buttonWiringSchematics[button]).forEach(counterIndex -> joltages[counterIndex]++);
        }
    }

    /**
     * Press as many buttons as you'd like.
     *
     * @param buttons The buttons you'd like to press.
     */
    public void pressButtons(int... buttons) {
        Arrays.stream(buttons).forEach(this::pressButton);
    }

    /**
     * Pull the lever to change the factory machine's configuration mode.
     */
    public void pullLever() {
        configurationMode = switch (configurationMode) {
            case ConfigurationMode.IndicatorLights -> ConfigurationMode.Joltages;
            case ConfigurationMode.Joltages -> ConfigurationMode.IndicatorLights;
        };
    }
}
