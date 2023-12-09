package io.github.maritims.aoc2022.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValvePath {
    private final Valve source;
    private final Valve destination;
    private final Integer distance;

    public ValvePath(Valve source, Valve destination, Integer distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public Valve getSource() {
        return source;
    }

    public Valve getDestination() {
        return destination;
    }

    public Integer getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "(source=" + getSource().getName() + ", destination=" + getDestination().getName() + ", distance=" + getDistance() + ")";
    }

    public static ValvePath fromValve(Map<Valve, Map<Valve, Integer>> paths, Valve source, Valve destination) {
        int distance = paths.get(source).get(destination);
        return new ValvePath(source, destination, distance);
    }

    public static ArrayList<ValvePath> fromValves(Map<Valve, Map<Valve, Integer>> paths, List<Valve> valves) {
        return valves.stream()
                .flatMap(s -> valves.stream().map(d -> fromValve(paths, s, d)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
