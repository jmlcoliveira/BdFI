package dataStructures.orderedLists;

import dataStructures.Comparator;
import dataStructures.DoubleListIterator;
import dataStructures.DoubleListNode;
import dataStructures.Iterator;

public class OrderedDoubleList<E extends Comparable<E>> implements OrderedList<E> {

    protected DoubleListNode<E> head;

    protected DoubleListNode<E> tail;

    private int currentSize;

    private final Comparator<E> comparator;

    public OrderedDoubleList() {
        head = null;
        tail = null;
        currentSize = 0;
        comparator = null;
    }

    public OrderedDoubleList(Comparator<E> comparator) {
        head = null;
        tail = null;
        currentSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }

    @Override
    public E find(E element) {
        DoubleListNode<E> node = findNode(element);
        return node == null ? null : node.getElement();
    }

    private DoubleListNode<E> findNode(E element) {
        DoubleListNode<E> node = head;
        while (node != null && compare(node.getElement(), element) <= 0) {

            if (node.getElement().equals(element))
                return node;
            node = node.getNext();

        }
        return null;
    }

    public E insert(E element) {
        DoubleListNode<E> node = head;
        DoubleListNode<E> next = null;
        DoubleListNode<E> previous = null;
        while (node != null) {
            E currentElement = node.getElement();

            if (compare(currentElement, element) == 0) {
                node.setElement(element);
                return currentElement;
            }
            if (compare(node.getElement(), element) > 0) {
                next = node;
                previous = node.getPrevious();
                break;
            }
            node = node.getNext();
        }

        DoubleListNode<E> newNode = new DoubleListNode<>(element);
        addNode(newNode, next, previous);

        return null;
    }

    private void addNode(DoubleListNode<E> newNode, DoubleListNode<E> next,
                         DoubleListNode<E> previous) {
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else if (next == null) {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        } else if (next == head) {
            head.setPrevious(newNode);
            newNode.setNext(head);
            head = newNode;
        } else {
            previous.setNext(newNode);
            newNode.setPrevious(previous);

            next.setPrevious(newNode);
            newNode.setNext(next);
        }
        currentSize++;
    }

    public E remove(E element) {
        DoubleListNode<E> node = head;
        while (node != null) {
            E currentElement = node.getElement();

            if (currentElement.equals(element)) {
                remove(node);
                return currentElement;
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    public E minElement() {
        return null;
    }

    @Override
    public E maxElement() {
        return null;
    }

    private void remove(DoubleListNode<E> node) {
        DoubleListNode<E> next = node.getNext();
        DoubleListNode<E> previous = node.getPrevious();

        if (head == tail) {
            head = null;
            tail = null;
        } else if (node == head) {
            next.setPrevious(null);
            head = next;
        } else if (node == tail) {
            previous.setNext(null);
            tail = previous;
        } else {
            next.setPrevious(previous);
            previous.setNext(next);
        }
        currentSize--;

    }

    private int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : e1.compareTo(e2);
    }

    public Iterator<E> iterator() {
        return new DoubleListIterator<>(head, tail);
    }
}