package io.github.maritims.aoc2022.day16;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.github.maritims.lib.FileHelper.getFileContent;

public class Valve {
    private final String name;
    private final Integer flowRate;
    private List<Valve> valves;
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

    public List<Valve> getValves() {
        return valves;
    }

    public void setValves(List<Valve> valves) {
        this.valves = valves;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static List<Valve> getFromFile(String filePath) {
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

        connections.forEach((key, value) -> valves.get(key)
                .setValves(value.stream()
                        .map(valves::get)
                        .collect(Collectors.toList())
                ));

        return new ArrayList<>(valves.values()).stream()
                .sorted(Comparator.comparing(Valve::getName))
                .collect(Collectors.toList());
    }
}
