package io.github.maritims.aoc2022.day16;

import io.github.maritims.aoc2022.Puzzle;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Solver extends Puzzle<Integer, Integer> {
    public static Valve getBestValve(Valve current, List<Valve> path) {
        // The path to the best valve can consist of more than one valve.
        // We need to find the shortest path with the best combined flow rate.
        // The valve at the end of that path is the best valve.
        // How do we know if a path is better than another path?

        for(Valve next : current.getValves()) {
            if(path.contains(next)) {
                continue;
            }
            path.add(next);
            getBestValve(next, path);
        }

        System.out.println(path);
        return path.size() > 0 ? path.get(path.size() - 1) : null;
    }

    @Override
    public Integer solvePartOne(String filePath) throws IOException {
        List<Valve> valves = Valve.fromFile(filePath);

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
                //currentValve = getBestValve(currentValve);
                System.out.println("You move to valve " + currentValve.getName() + ".\n");
                continue;
            }

            if(!currentValve.isOpen()) {
                currentValve.setOpen(true);
                System.out.println("You open valve " + currentValve.getName() + ".\n");
                continue;
            }

            //currentValve = getBestValve(currentValve);
            System.out.println("You move to valve " + currentValve.getName() + ".\n");
        }

        return totalPressureRelease;
    }

    @Override
    public Integer solvePartTwo(String filePath) throws IOException {
        return null;
    }
}
