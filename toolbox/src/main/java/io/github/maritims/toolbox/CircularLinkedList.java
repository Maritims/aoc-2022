package io.github.maritims.toolbox;

import java.util.LinkedList;

public class CircularLinkedList<T> extends LinkedList<T> {
    @Override
    public T get(int index) {
        var actualIndex = index % size();
        return super.get(actualIndex);
    }
}
