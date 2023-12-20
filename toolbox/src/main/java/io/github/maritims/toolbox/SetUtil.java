package io.github.maritims.toolbox;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SetUtil {
    protected static <T> HashSet<Set<T>> addElementToPowerSet(Set<Set<T>> powerSetOfSubsetWithoutElement, T element) {
        var powerSetOfSubsetWithElement = new HashSet<Set<T>>();
        for(var subsetWithoutElement : powerSetOfSubsetWithoutElement) {
            var subsetWithElement = new HashSet<>(subsetWithoutElement);
            subsetWithElement.add(element);
            powerSetOfSubsetWithElement.add(subsetWithElement);
        }
        return powerSetOfSubsetWithElement;
    }

    /**
     * Calculates the power set of the given {@link Set} recursively.
     */
    public static <T> Set<Set<T>> getPowerSet(Set<T> set) {
        if (set.isEmpty()) {
            return new HashSet<>(Set.of(set));
        }

        var element = set.iterator().next();
        var subsetWithoutElement = set.stream()
            .filter(s -> !s.equals(element))
            .collect(Collectors.toCollection(HashSet::new));
        var powerSetOfSubsetWithoutElement = getPowerSet(subsetWithoutElement);
        var powerSetOfSubsetWithElement    = addElementToPowerSet(powerSetOfSubsetWithoutElement, element);
        var powerSet                       = new HashSet<Set<T>>();

        powerSet.addAll(powerSetOfSubsetWithoutElement);
        powerSet.addAll(powerSetOfSubsetWithElement);

        return powerSet;
    }
}
