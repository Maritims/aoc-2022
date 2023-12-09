package io.github.maritims.aoc2022.day16;

import java.util.*;
import java.util.stream.Collectors;

public class BreadthFirstSearch {
    /**
     * Use BFS to get the shortest distance from any given valve to any other valve that valve is connected to.
     * @param valve The valve to use as an origin point for calculating distances to all other valves.
     * @return A map of each valve and its distance from the origin point, i.e. the given valve {@code Valve}.
     */
    public Map<Valve, Integer> getPaths(Valve valve) {
        Map<Valve, Integer> distances = new HashMap<>();
        distances.put(valve, 0);
        LinkedList<Valve> queue = new LinkedList<>();
        queue.add(valve);

        while(!queue.isEmpty()) {
            // Poll a valve from the queue to avoid getting stuck on it.
            Valve current = queue.poll();

            // Inspect all neighbours of the current valve.
            for(Valve neighbour : current.getValves()) {
                // We've reached a neighbour we've visited before.
                // Skip it to avoid going in circles.
                if(distances.containsKey(neighbour)) {
                    continue;
                }

                // The distance to the neighbour is naturally one step away from the distance to the current valve.
                distances.put(neighbour, distances.get(current) + 1);

                // Add the neighbour to the queue, so we can inspect its neighbours.
                queue.add(neighbour);
            }
        }

        return distances;
    }

    /**
     * Get the shortest distance from every valve to any other valve that valve is connected to.
     * @param valves All valves to check.
     * @return A map of the origin valve with a corresponding map of the shortest distance to every valve it's connected to.
     */
    public Map<Valve, Map<Valve, Integer>> getPaths(List<Valve> valves) {
        return valves.stream()
                .collect(Collectors.toMap(valve -> valve, this::getPaths, (current, next) -> next));
    }

    public ArrayList<Valve> getActiveValves(List<Valve> valves, int minFlowRate) {
        ArrayList<Valve> activeValves = new ArrayList<>();
        activeValves.add(valves.get(0));
        valves.stream()
                .filter(valve -> valve.getFlowRate() > minFlowRate && !"AA".equalsIgnoreCase(valve.getName()))
                .forEach(activeValves::add);
        return activeValves;
    }


    /**
     * Get data for active valves
     * @param valves All valves to retrieve data for.
     * @return Distances and flow rates from every valve to each connected valve.
     */
    public ArrayList<ValvePath> getActiveValvePaths(List<Valve> valves) {
        Map<Valve, Map<Valve, Integer>> paths = getPaths(valves);
        ArrayList<Valve> activeValves = getActiveValves(valves, 1);
        return ValvePath.fromValves(paths, activeValves);
    }

    public Map<State, Integer> getNextStates(State state, Data data) {
        Map<State, Integer> nextStates = new HashMap<>();
        for(int i = 0; i < data.getPaths().size(); i++) {
            if(state.getOpenValves()[i]) {
                continue; // The valve is open already. Move to the next one.
            }

            int remainingTime = state.getRemainingTime() - 1; // It takes a minute to open a valve.
            if(0 >= remainingTime) {
                continue; // Time's up!
            }

            int pressureReleased = remainingTime * data.getPaths().get(i).getSource().getFlowRate();

            boolean[] openValves = state.getOpenValves().clone();
            openValves[i] = true; // Open the valve.

            State newState = new State(i, remainingTime, openValves);
            nextStates.put(newState, pressureReleased);
        }
        return nextStates;
    }

    public void setMaximalPressure(State state, Data data) {
        if(data.getMaximalPressure().containsKey(state)) {
            return;
        }

        int maximalPressure;
        for(Map.Entry<State, Integer> entry : getNextStates(state, data).entrySet()) {
            setMaximalPressure(entry.getKey(), data);
            int totalPressureReleased = data.getMaximalPressure().get(entry.getKey()) + entry.getValue();
            maximalPressure = Math.max(entry.getValue(), totalPressureReleased);
            data.getMaximalPressure().put(entry.getKey(), maximalPressure);
        }
    }
}
