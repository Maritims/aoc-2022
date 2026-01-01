package io.github.maritims.advent_of_code.common.geometry.visualization;

import io.github.maritims.advent_of_code.common.geometry.Point2D;
import io.github.maritims.advent_of_code.common.geometry.Polygon;
import io.github.maritims.advent_of_code.common.geometry.Rectangle;

import java.util.*;

public class PolygonPacker {
    private final Rectangle           grid;
    private final List<List<Polygon>> polygonOrientations;

    public PolygonPacker(Rectangle grid, List<Polygon> polygons) {
        if (grid == null) {
            throw new IllegalArgumentException("grid cannot be null");
        }
        if (polygons == null || polygons.isEmpty()) {
            throw new IllegalArgumentException("polygons cannot be null or empty");
        }
        this.grid                = grid;
        this.polygonOrientations = polygons.stream()
                .map(this::generateUniqueOrientations)
                .toList();
    }

    List<Polygon> generateUniqueOrientations(Polygon polygon) {
        if (polygon == null) {
            throw new IllegalArgumentException("polygon cannot be null");
        }

        var seen    = new HashSet<List<Point2D>>();
        var unique  = new ArrayList<Polygon>();
        var current = polygon.normalize();

        for(var i = 0; i < 4; i++) {
            current = current.rotate90().normalize();
            var sortedVertices = current.getVertices()
                    .stream()
                    .sorted(Comparator.comparing(Point2D::col).thenComparing(Point2D::row))
                    .toList();

            if (seen.add(sortedVertices)) {
                unique.add(current);
            }
        }

        return unique;
    }

    boolean isValidPlacement(Polygon polygon, Set<Point2D> occupied) {
        for(var vertex : polygon.getVertices()) {
            // Check grid bounds.
            if(vertex.col() < 0 || vertex.col() >= grid.getWidth() || vertex.row() < 0 || vertex.row() >= grid.getHeight()) {
                return false;
            }

            // Check collision with other polygons.
            if (occupied.contains(vertex)) {
                return false;
            }
        }

        return true;
    }

    List<Polygon> backtrack(Set<Point2D> occupied, int polygonIndex) {
        // Base case:: All polygons have been placed.
        if (polygonIndex == polygonOrientations.size()) {
            return new ArrayList<>();
        }

        for(var orientation : polygonOrientations.get(polygonIndex)) {
            for(var row = 0; row < grid.getHeight(); row++) {
                for(var col = 0; col < grid.getWidth(); col++) {
                    var placed = orientation.move(col, row);
                    if (isValidPlacement(placed, occupied)) {
                        var polygonPoints = new HashSet<>(placed.getVertices());
                        occupied.addAll(polygonPoints);

                        var result = backtrack(occupied, polygonIndex + 1);

                        if (result != null) {
                            result.add(placed);
                            return result;
                        }

                        occupied.removeAll(polygonPoints);
                    }
                }
            }
        }

        return null;
    }

    public List<Polygon> pack() {
        return backtrack(new HashSet<>(), 0);
    }
}
