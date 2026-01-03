package io.github.maritims.advent_of_code.common.util;

public class IntegerUtil {
    public static int[][] toPrimitiveArray(Integer[][] boxedArray, int defaultValue) {
        if (boxedArray == null) {
            throw new IllegalArgumentException("boxedArray cannot be null");
        }

        var unboxedArray = new int[boxedArray.length][];
        for(var i = 0; i < boxedArray.length; i++) {
            if (boxedArray[i] == null) {
                unboxedArray[i] = null;
            } else {
                unboxedArray[i] = new int[boxedArray[i].length];
                for(var j = 0; j < boxedArray[i].length; j++) {
                    unboxedArray[i][j] = boxedArray[i][j] == null ? defaultValue : boxedArray[i][j];
                }
            }
        }
        return unboxedArray;
    }
}
