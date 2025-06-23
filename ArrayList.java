package utils;

import java.util.Arrays;
import java.util.Objects;

public class ArrayList<E> implements List<E> {

    public static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size;

    
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Capacity must be >= 0");
        data = (E[]) new Object[capacity];
        size = 0;
    }

    
    public ArrayList(List<E> list) {
        this(list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public boolean add(E item) {
        validateCapacity(size + 1);
        data[size++] = item;
        return true;
    }

    @Override
    public void add(int index, E item) {
        checkIndexForAdd(index);
        validateCapacity(size + 1);
        shiftRight(index);
        data[index] = item;
        size++;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(E item) {
        return indexOf(item) != -1;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public int indexOf(E item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removed = data[index];
        shiftLeft(index);
        size--;
        return removed;
    }

    @Override
    public boolean remove(E item) {
        int index = indexOf(item);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);
        E old = data[index];
        data[index] = item;
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof List)) return false;
        List<?> other = (List<?>) obj;
        if (this.size != other.size()) return false;
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(this.get(i), other.get(i))) return false;
        }
        return true;
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);
    }

    private void shiftLeft(int index) {
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
    }

    private void shiftRight(int index) {
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
    }

    public void validateCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length * 2 + 1, minCapacity);
            data = Arrays.copyOf(data, newCapacity);
        }
    }
}