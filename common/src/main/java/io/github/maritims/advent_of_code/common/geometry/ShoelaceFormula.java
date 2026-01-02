package io.github.maritims.advent_of_code.common.geometry;

import io.github.maritims.advent_of_code.common.tuples.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class ShoelaceFormula {
    /**
     * Multiply the col,row coordinates of all the points diagonally.
     */
    protected Tuple2<List<Integer>, List<Integer>> getLeftAndRightProducts(List<Point> points) {
        var left  = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();

        for (var i = 0; i < points.size() - 1; i++) {
            var p1 = points.get(i);
            var p2 = points.get(i + 1);

            left.add(p1.row() * p2.column());
            right.add(p2.row() * p1.column());
        }

        return new Tuple2<>(left, right);
    }

    protected int getSum(List<Integer> products) {
        return products.stream().reduce(0, Integer::sum);
    }

    /**
     * Calculate the interior area of an irregular polygon by using the shoelace formula.
     * This method creates a copy of the original list and adds the first element to the end of that list as the shoelace formula requires that the list of points circles around.
     * @return The area of the irregular polygon described by the given points.
     */
    public double calculate(List<Point> points) {
        var copy = new ArrayList<>(points);
        copy.add(points.get(0));

        var leftAndRightProducts = getLeftAndRightProducts(copy);
        var leftSum              = getSum(leftAndRightProducts.item1());
        var rightSum             = getSum(leftAndRightProducts.item2());
        var difference           = Math.abs(leftSum - rightSum);

        return difference * 0.5;
    }

    public double calculate2(List<Point2D> points) {
        return calculate(points.stream().map(p -> new Point((int) p.row(), (int) p.col())).toList());
    }
}