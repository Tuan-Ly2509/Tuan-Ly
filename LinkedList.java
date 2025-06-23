package utils;

import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public LinkedList(List<E> list) {
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
        last = null;
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
        if (index < size / 2) {
            Node<E> current = first;
            for (int i = 0; i < index; i++) current = current.next;
            return current;
        } else {
            Node<E> current = last;
            for (int i = size - 1; i > index; i--) current = current.prev;
            return current;
        }
    }

    private void append(E item) {
        Node<E> newNode = new Node<>(last, item, null);
        if (last != null) last.next = newNode;
        last = newNode;
        if (first == null) first = newNode;
    }

    private E detach(int index) {
        Node<E> target = node(index);
        if (target.prev != null) {
            target.prev.next = target.next;
        } else {
            first = target.next;
        }

        if (target.next != null) {
            target.next.prev = target.prev;
        } else {
            last = target.prev;
        }

        return target.data;
    }

    private void insertBefore(int index, E item) {
        if (index == size) {
            append(item);
        } else {
            Node<E> nextNode = node(index);
            Node<E> prevNode = nextNode.prev;
            Node<E> newNode = new Node<>(prevNode, item, nextNode);
            if (prevNode != null) prevNode.next = newNode;
            else first = newNode;
            nextNode.prev = newNode;
        }
    }

    
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
}