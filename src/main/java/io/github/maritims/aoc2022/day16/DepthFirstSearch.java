package io.github.maritims.aoc2022.day16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirstSearch<T> {
    private final List<Valve> neighbours;

    public DepthFirstSearch(List<Valve> neighbours) {
        this.neighbours = neighbours;
    }

    public List<Valve[]> getAllPaths(Valve source, Valve destination) {
        List<Valve> visited = new ArrayList<>();
        List<Valve> path = new ArrayList<>();
        path.add(source);
        return getAllPaths(source, destination, visited, path, new ArrayList<>());
    }

    private List<Valve[]> getAllPaths(Valve source, Valve destination, List<Valve> visited, List<Valve> path, List<Valve[]> paths) {
        if(source.equals(destination)) {
            return Collections.singletonList(path.toArray(new Valve[0]));
        }

        // Keep track of the source, so we don't drop by again while going through the neighbours.
        visited.add(source);

        // These are all potential neighbours.
        for(Valve neighbour : neighbours) {
            // Have we visited this neighbour before?
            if(visited.contains(neighbour)) {
                continue;
            }

            path.add(neighbour);
            List<Valve[]> newPaths = getAllPaths(neighbour, destination, visited, path, paths);
            if(newPaths != null && !paths.contains(newPaths.get(0))) {
                paths.addAll(newPaths);
            }
            path.remove(neighbour);
        }

        // We want to visit the source again.
        visited.remove(source);
        return paths;
    }
}
