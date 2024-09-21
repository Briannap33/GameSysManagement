package com.example.gamesystemmanagement.controllers;

import java.util.Iterator;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MyLinkedList<E> implements Iterable<E> {

    private LinkedNode<E> head = null;

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    public void add(E element) {
        LinkedNode<E> ln = new LinkedNode<>();
        ln.setContents(element);
        ln.setNext(head);
        head = ln;
    }

    public boolean remove(E element) {
        if (head == null)
            return false;

        if (head.getContents().equals(element)) {
            head = head.getNext();
            return true;
        }

        LinkedNode<E> current = head;
        LinkedNode<E> previous = null;

        while (current != null && !current.getContents().equals(element)) {
            previous = current;
            current = current.getNext();
        }

        if (current == null)
            return false;

        previous.setNext(current.getNext());
        return true;
    }

    public void addAll(MyLinkedList<E> other) {
        for (E element : other) {
            add(element);
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void delete(E element) {
        if (head == null)
            return;

        if (head.getContents().equals(element)) {
            head = head.getNext();
            return;
        }

        LinkedNode<E> current = head;
        LinkedNode<E> previous = null;

        while (current != null && !current.getContents().equals(element)) {
            previous = current;
            current = current.getNext();
        }

        if (current != null)
            previous.setNext(current.getNext());
    }

    public E get(int index) {
        LinkedNode<E> temp = getNodeAtIndex(index);
        if (temp == null)
            throw new IndexOutOfBoundsException();
        return temp.getContents();
    }

    private LinkedNode<E> getNodeAtIndex(int index) {
        LinkedNode<E> temp = head;
        int i = 0;
        while (i < index && temp != null) {
            temp = temp.getNext();
            i++;
        }
        return temp;
    }

    public Stream<Object> stream() {
        return null;
    }

    public int size() {
        LinkedNode<E> temp = head;
        int i = 0;
        while (temp != null) {
            temp = temp.getNext();
            i++;
        }
        return i;
    }

    public void set(int minIndex, E game) {
        LinkedNode<E> temp = head;
        int i = 0;
        while (i < minIndex && temp != null) {
            temp = temp.getNext();
            i++;
        }
        if (temp == null)
            throw new IndexOutOfBoundsException();
        temp.setContents(game);
    }

    // Other methods remain unchanged

    private class MyLinkedListIterator implements Iterator<E> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }
        // Implementation of the iterator
    }

    private class LinkedNode<E> {
        private MyLinkedList<E>.LinkedNode<E> next;

        public void setContents(E element) {
        }

        public void setNext(LinkedNode<E> next) {
            this.next = (MyLinkedList<E>.LinkedNode<E>) next;
        }

        public MyLinkedList<E>.LinkedNode<E> getNext() {
            return next;
        }



        public E getContents() {
            return null;
        }
    }
}