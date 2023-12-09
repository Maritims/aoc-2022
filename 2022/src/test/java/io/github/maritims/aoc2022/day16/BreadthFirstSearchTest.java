package io.github.maritims.aoc2022.day16;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BreadthFirstSearchTest {
    final BreadthFirstSearch sut = new BreadthFirstSearch();
    final List<Valve> valves = Valve.getFromFile("16/example.txt");

    @Test
    public void getShortestDistances() {
        Map<Valve, Integer> distances = sut.getPaths(valves.get(0));
        System.out.println(distances);
    }

    @Test
    public void getAllShortestDistances() {
        Map<Valve, Map<Valve, Integer>> distances = sut.getPaths(valves);
        distances.entrySet().forEach(System.out::println);
    }

    @Test
    public void getActiveValves() {
        List<Valve> activeValves = sut.getActiveValves(valves, 1);
        System.out.println(activeValves);
    }

    @Test
    public void getActiveValveDistances() {
        List<ValvePath> activeValvePaths = sut.getActiveValvePaths(valves);
        activeValvePaths.forEach(System.out::println);
    }

    @Test
    public void getNextStates() {
        List<ValvePath> paths = sut.getActiveValvePaths(valves);
        State state = new State(0, 30, new boolean[paths.size()]);
        int[] flowRate = paths.stream().map(ValvePath::getSource).mapToInt(Valve::getFlowRate).toArray();
        Data data = new Data(new HashMap<>(), paths);
        System.out.println(sut.getNextStates(state, data));
        System.out.println(sut.getNextStates(state, data));
    }

    @Test
    public void setMaximalPressure() {
        List<ValvePath> paths = sut.getActiveValvePaths(valves);
        State state = new State(0, 30, new boolean[paths.size()]);
        int[] flowRate = paths.stream().map(ValvePath::getSource).mapToInt(Valve::getFlowRate).toArray();
        Data data = new Data(new HashMap<>(), paths);
        sut.setMaximalPressure(state, data);
    }
}