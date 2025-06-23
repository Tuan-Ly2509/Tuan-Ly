package utils;

import java.util.NoSuchElementException;

public interface List<E> {

    
    boolean add(E item);
    void add(int index, E item);
    void clear();
    boolean contains(E item);
    E get(int index);
    int indexOf(E item);
    boolean isEmpty();
    E remove(int index);
    boolean remove(E item);
    E set(int index, E item);
    int size();

    
    default boolean addAll(List<E> items) {
        boolean added = false;
        for (int i = 0; i < items.size(); i++) {
            added |= this.add(items.get(i));
        }
        return added;
    }

    default void addFirst(E item) {
        add(0, item);
    }

    default void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    default E getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return get(0);
    }

    default E getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return get(size() - 1);
    }

    default boolean removeAll(List<E> items) {
        boolean removed = false;
        for (int i = 0; i < items.size(); i++) {
            removed |= this.remove(items.get(i));
        }
        return removed;
    }

    default E removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return remove(0);
    }

    default E removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return remove(size() - 1);
    }

    
    default Iterator<E> iterator() {
        return new Iterator<>() {
            int index = 0;
            boolean isRemovable = false;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                isRemovable = true;
                return get(index++);
            }

            @Override
            public void remove() {
                if (!isRemovable) throw new IllegalStateException();
                List.this.remove(--index);
                isRemovable = false;
            }
        };
    }
}