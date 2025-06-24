package utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T>, Iterable<T> {
    private Object[] data = new Object[10];
    private int size = 0;

    public void add(T item) {
        if (size == data.length) resize();
        data[size++] = item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return (T) data[index];
    }

    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        for (int i = index; i < size - 1; i++) data[i] = data[i + 1];
        data[--size] = null;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    private void resize() {
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;
            public boolean hasNext() { return current < size; }
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (T) data[current++];
            }
        };
    }
}
