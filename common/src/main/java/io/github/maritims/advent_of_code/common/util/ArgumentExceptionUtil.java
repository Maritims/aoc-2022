package io.github.maritims.advent_of_code.common.util;

import java.util.List;

public class ArgumentExceptionUtil {
    public static <T> T throwIfNull(T argument, String argumentName) {
        if (argument == null) {
            throw new IllegalArgumentException("Argument '" + argumentName + "' cannot be null");
        }

        return argument;
    }

    public static int throwIfNullOrNegative(Integer argument, String argumentName) {
        if (argument == null || argument < 0) {
            throw new IllegalArgumentException("Argument '" + argumentName + "' cannot be null or negative");
        }

        return argument;
    }

    public static String throwIfNullOrEmpty(String argument, String argumentName) {
        if (argument == null || argument.isBlank()) {
            throw new IllegalArgumentException("Argument '" + argumentName + "' cannot be null or empty");
        }
        return argument;
    }

    public static List<?> throwIfNullOrEmpty(List<?> argument, String argumentName) {
        if (argument == null || argument.isEmpty()) {
            throw new IllegalArgumentException("Argument '" + argumentName + "' cannot be null or empty");
        }
        return argument;
    }
}
