package io.github.maritims.aoc2023.day5;

import java.util.List;
import java.util.stream.Collectors;

public class PiecewiseFunction {
    private final List<Piece> pieces;

    public PiecewiseFunction(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Double evaluate(double x) {
        for(var piece : pieces) {
            var result = piece.evaluate(x);
            if(result != null) {
                return result;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "f(x) {\n"
            + pieces.stream().map(piece -> "\t\t" + piece.toString()).collect(Collectors.joining("\n"))
            + "\n}";
    }
}
