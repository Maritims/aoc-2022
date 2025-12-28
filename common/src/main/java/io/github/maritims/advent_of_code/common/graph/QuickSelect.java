package io.github.maritims.advent_of_code.common.graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class implementing the QuickSelect algorithm (Hoare's selection algorithm).
 * <p>
 * QuickSelect is used to find the k-th smallest element in an unordered list without
 * fully sorting the list. It is particularly efficient for finding medians in
 * spatial structures like KD-Trees.
 * </p>
 */
public class QuickSelect {
    private QuickSelect() {
    }

    /**
     * <p>Rearrange the elements so that the element at index {@code k} is the same element that would be there if the entired list were sorted.</p>
     * <p>The original list is mutated by this method.</p>
     * <p>All elements to the left of {@code k} will be less than or equal to the element at {@code k}.</p>
     * <p>All elements to the right of {@code k} will be greater than or equal to the element at {@code k}.</p>
     *
     * @param elements   The list to be partitioned. Must not be null or empty.
     * @param k          The target index to be correctly positioned (as if the entire list were sorted). 0-based.
     * @param comparator The {@link Comparator} to use for comparing elements. Must not be null.
     * @param <T>        The type of the elements in the list.
     */
    public static <T> void select(List<T> elements, int k, Comparator<? super T> comparator) {
        if (elements == null || elements.isEmpty()) {
            return;
        }
        if (k < 0 || k >= elements.size()) {
            throw new IndexOutOfBoundsException("Target index k (" + k + ") is out of bounds for list size " + elements.size());
        }

        var left = 0;
        var right = elements.size() - 1;

        while (right > left) {
            var pivotIndex = partition(elements, left, right, comparator);
            if (pivotIndex == k) {
                return;
            }

            if (pivotIndex > k) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    /**
     * Partitions the list into two parts based on a pivot element.
     *
     * @param elements   The list to be partitioned.
     * @param left       The index of the first element to consider.
     * @param right      The index of the last element to consider.
     * @param comparator The {@link Comparator} to use for comparing elements.
     * @param <T>        The type of the elements in the list.
     * @return The final index of the pivot element.
     */
    private static <T> int partition(List<T> elements, int left, int right, Comparator<? super T> comparator) {
        var pivot = elements.get(right);
        var storeIndex = left;

        for (var i = left; i < right; i++) {
            if (comparator.compare(elements.get(i), pivot) < 0) {
                Collections.swap(elements, storeIndex, i);
                storeIndex++;
            }
        }
        Collections.swap(elements, storeIndex, right);
        return storeIndex;
    }
}
