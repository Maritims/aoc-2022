package io.github.maritims.advent_of_code.common;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class DynamicProgramming {
    public static <T> T findOptimalResult(
            T[] elements,
            int K,
            BiFunction<T, T, T> onSelectCurrent,
            Supplier<T> onInsufficientElements,
            Supplier<T> defaultValueSupplier,
            Supplier<T> sentinelValueSupplier,
            Comparator<T> comparator
    ) {
        var N = elements.length;
        if (N < K) {
            // Not enough elements in total to turn on K elements.
            return onInsufficientElements.get();
        }
        var DEFAULT_VALUE  = defaultValueSupplier.get();
        var SENTINEL_VALUE = sentinelValueSupplier.get();

        // DP table: DP[i][k] = max value that can be formed by 'k' digits from the first 'i' elements.
        @SuppressWarnings("unchecked") var DP = (T[][]) new Object[N + 1][K + 1];

        for (var i = 0; i <= N; i++) {
            DP[i][0] = DEFAULT_VALUE;
        }
        for (var k = 1; k <= K; k++) {
            DP[0][k] = SENTINEL_VALUE;
        }

        for (var i = 1; i <= N; i++) {
            var currentElement = elements[i - 1];

            // Iterate through the selected elements (1 to K, inclusive).
            for (var k = 1; k <= K; k++) {
                var skipCurrent   = DP[i - 1][k];
                var selectCurrent = SENTINEL_VALUE;

                if (k <= i) {
                    selectCurrent = onSelectCurrent.apply(DP[i - 1][k - 1], currentElement);
                }

                DP[i][k] = comparator.compare(selectCurrent, skipCurrent) > 0 ? selectCurrent : skipCurrent;
            }
        }

        return DP[N][K];
    }
}
