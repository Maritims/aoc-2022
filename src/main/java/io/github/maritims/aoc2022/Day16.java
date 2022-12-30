package io.github.maritims.aoc2022;

import java.io.IOException;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Day16 extends Puzzle<Integer, Integer> {

    public static final Pattern PATTERN = Pattern.compile("([A-Z]{2}|\\d+)");

    static class Valve {
        private final String name;
        private final Integer flowRate;
        private List<Valve> adjacent;
        private boolean isOpen = false;

        Valve(String name, Integer flowRate) {
            this.name = name;
            this.flowRate = flowRate;
        }

        public String getName() {
            return name;
        }

        public Integer getFlowRate() {
            return flowRate;
        }

        public boolean isConnectedTo(Valve valve) {
            return adjacent.stream().anyMatch(adjacentValve -> adjacentValve.getName().equalsIgnoreCase(valve.getName()));
        }

        @Override
        public String toString() {
            return name;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean open) {
            isOpen = open;
        }

        public void setAdjacent(List<Valve> adjacent) {
            this.adjacent = adjacent;
        }
    }

    public static List<Valve> getValves(String filePath) {
        Map<String, List<String>> connections = new HashMap<>();
        Map<String, Valve> valves = new HashMap<>();
        Pattern pattern = Pattern.compile("([A-Z]{2}|\\d+)");
        getFileContent(filePath).stream().map(line -> pattern
                .matcher(line)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new)).forEach(parts -> {
            Valve valve = new Valve(parts[0], Integer.parseInt(parts[1]));
            connections.put(valve.getName(), Arrays.asList(parts).subList(2, parts.length));
            valves.put(valve.getName(), valve);
        });

        connections.forEach((key, value) -> {
            valves.get(key)
                    .setAdjacent(value.stream()
                            .map(valves::get)
                            .collect(Collectors.toList())
                    );
        });

        return new ArrayList<>(valves.values()).stream()
                .sorted(Comparator.comparing(Valve::getName))
                .collect(Collectors.toList());
    }

    public static Valve getBestValve(List<Valve> valves, Valve currentValve) {
        // The path to the best valve can consist of more than one valve.
        // We need to find the shortest path with the best combined flow rate.
        // The valve at the end of that path is the best valve.

        // Best immediate valve.
        return valves.stream()
                .filter(currentValve::isConnectedTo)
                .max(Comparator.comparingInt(Valve::getFlowRate))
                .orElse(null);
    }

    @Override
    public Integer solvePartOne(String filePath) throws IOException {
        List<Valve> valves = getValves(filePath);

        Valve currentValve = valves.get(0);
        int totalPressureRelease = 0;
        for(int minute = 0; minute < 30; minute++) {
            System.out.println("== Minute " + (minute + 1) + " ==");

            if(valves.stream().noneMatch(Valve::isOpen)) {
                System.out.println("No valves are open.");
            }

            List<Valve> openValves = valves.stream()
                    .filter(Valve::isOpen)
                    .collect(Collectors.toList());
            if(openValves.size() > 0) {
                int pressureRelease = openValves.stream().mapToInt(Valve::getFlowRate).sum();
                totalPressureRelease += pressureRelease;
                System.out.println("Valves " + openValves.stream().map(Valve::getName).collect(Collectors.joining(", ")) + " are open, releasing " + pressureRelease + " pressure.");
            }

            if(currentValve.getFlowRate() == 0) {
                currentValve = getBestValve(valves, currentValve);
                System.out.println("You move to valve " + currentValve.getName() + ".\n");
                continue;
            }

            if(!currentValve.isOpen()) {
                currentValve.setOpen(true);
                System.out.println("You open valve " + currentValve.getName() + ".\n");
                continue;
            }

            currentValve = getBestValve(valves, currentValve);
            System.out.println("You move to valve " + currentValve.getName() + ".\n");
        }

        return totalPressureRelease;
    }

    @Override
    public Integer solvePartTwo(String filePath) throws IOException {
        return null;
    }
}
