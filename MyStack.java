package utils;

import java.util.EmptyStackException;

public class MyStack<E> {

    private Node<E> first;
    private int size;

    public MyStack() {
        first = null;
        size = 0;
    }

    public E push(E item) {
        first = new Node<>(item, first);
        size++;
        return item;
    }

    public E pop() {
        if (isEmpty()) throw new EmptyStackException();
        return detach();
    }

    public E peek() {
        if (isEmpty()) throw new EmptyStackException();
        return first.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private E detach() {
        E removed = first.data;
        first = first.next;
        size--;
        return removed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("bottom [");
        Node<E> current = first;
        E[] array = (E[]) new Object[size];
        int i = size - 1;
        while (current != null) {
            array[i--] = current.data;
            current = current.next;
        }
        for (int j = 0; j < size; j++) {
            sb.append(array[j]);
            if (j < size - 1) sb.append(", ");
        }
        sb.append("] top");
        return sb.toString();
    }

    
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}