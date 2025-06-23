package utils;

import java.util.NoSuchElementException;

public class MyQueue<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public MyQueue() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean add(E item) {
        append(item);
        size++;
        return true;
    }

    public E remove() {
        if (isEmpty()) throw new NoSuchElementException();
        E removed = detach();
        size--;
        return removed;
    }

    public E peek() {
        return isEmpty() ? null : first.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void append(E item) {
        Node<E> newNode = new Node<>(item);
        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    private E detach() {
        E removed = first.data;
        first = first.next;
        if (first == null) last = null;
        return removed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("front [");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("] back");
        return sb.toString();
    }

    
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
}