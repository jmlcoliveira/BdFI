package dataStructures;

public class OrderedDoubleList<E extends Comparable<E>> implements OrderedList<E> {

    protected DoubleListNode<E> head;

    protected DoubleListNode<E> tail;

    private int currentSize;

    public OrderedDoubleList() {
        head = null;
        tail = null;
        currentSize = 0;
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
        while (node != null && node.getElement().compareTo(element) <= 0) {

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

            if (currentElement.equals(element)) {
                node.setElement(element);
                return currentElement;
            }
            if (node.getElement().compareTo(element) > 0) {
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

    public Iterator<E> iterator() {
        return new DoubleListIterator<>(head, tail);
    }
}
