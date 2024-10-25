package de.medieninformatik.listeserialisieren;

import java.util.AbstractList;

public class NichtSerialisierteListe<T> extends AbstractList<T>{

    private final T[] elements;

    @SuppressWarnings("unchecked")
    public NichtSerialisierteListe(T... elements) {
        this.elements = elements;
    }

    @Override
    public T get(int index) {
        return elements[index];
    }

    @Override
    public int size() {
        return elements.length;
    }
}