package utils;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> implements List<E> {

    private Node<E> first;
    private int size;

    public SinglyLinkedList() {
        first = null;
        size = 0;
    }

    public SinglyLinkedList(List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public boolean add(E item) {
        append(item);
        size++;
        return true;
    }

    @Override
    public void add(int index, E item) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        insertBefore(index, item);
        size++;
    }

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public boolean contains(E item) {
        return indexOf(item) != -1;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return node(index).data;
    }

    @Override
    public int indexOf(E item) {
        Node<E> current = first;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.data, item)) return i;
            current = current.next;
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
        E removed = detach(index);
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
        Node<E> n = node(index);
        E old = n.data;
        n.data = item;
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof List)) return false;
        List<?> other = (List<?>) obj;
        if (this.size != other.size()) return false;
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(this.get(i), other.get(i))) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = first;
        for (int i = 0; i < size; i++) {
            sb.append(current.data);
            if (i < size - 1) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    private Node<E> node(int index) {
        Node<E> current = first;
        for (int i = 0; i < index; i++) current = current.next;
        return current;
    }

    private void append(E item) {
        Node<E> newNode = new Node<>(item);
        if (first == null) {
            first = newNode;
        } else {
            Node<E> current = first;
            for (int i = 0; i < size - 1; i++) current = current.next;
            current.next = newNode;
        }
    }

    private E detach(int index) {
        if (index == 0) {
            E data = first.data;
            first = first.next;
            return data;
        } else {
            Node<E> prev = node(index - 1);
            E data = prev.next.data;
            prev.next = prev.next.next;
            return data;
        }
    }

    private void insertBefore(int index, E item) {
        Node<E> newNode = new Node<>(item);
        if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node<E> prev = node(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
    }

   
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this(data, null);
        }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}