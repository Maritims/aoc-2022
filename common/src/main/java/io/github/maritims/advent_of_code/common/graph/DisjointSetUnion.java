package io.github.maritims.advent_of_code.common.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisjointSetUnion<T> {
    private final Map<T, T> parent = new HashMap<>();
    private final Map<T, Integer> size = new HashMap<>();
    private int numberOfSets;

    public DisjointSetUnion(Collection<T> elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements cannot be null");
        }
        for(var element : elements) {
            parent.put(element, element);
            size.put(element, 1);
        }
        numberOfSets = elements.size();
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public List<Integer> getSizes() {
        return parent.keySet()
                .stream()
                .filter(element -> parent.get(element).equals(element))
                .map(size::get)
                .toList();
    }

    public T find(T element) {
        if (parent.get(element).equals(element)) {
            return element;
        }

        var root = find(parent.get(element));
        parent.put(element, root);
        return root;
    }

    public void union(T element, T other) {
        var root1 = find(element);
        var root2 = find(other);

        if (!root1.equals(root2)) {
            if (size.get(root1) < size.get(root2)) {
                var temp = root1;
                root1 = root2;
                root2 = temp;
            }
            parent.put(root2, root1);
            size.put(root1, size.get(root1) + size.get(root2));
            numberOfSets--;
        }
    }
}
