package io.github.maritims.aoc2023.day5;

import java.util.List;
import java.util.stream.Collectors;

public class PiecewiseFunction {
    private final List<Piece> pieces;

    public PiecewiseFunction(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Double evaluate(double x) {
        return pieces.stream()
            .filter(piece -> piece.isApplicable(x))
            .map(piece -> piece.evaluate(x))
            .findFirst()
            .orElse(x);
    }

    @Override
    public String toString() {
        return "f(x) {\n"
            + pieces.stream().map(piece -> "\t\t" + piece.toString()).collect(Collectors.joining("\n"))
            + "\n}";
    }
}
