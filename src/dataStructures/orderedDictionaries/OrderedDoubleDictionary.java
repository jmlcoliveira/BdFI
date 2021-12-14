package dataStructures.orderedDictionaries;

import dataStructures.*;
import dataStructures.exceptions.EmptyDictionaryException;
import dataStructures.exceptions.NoSuchElementException;

public class OrderedDoubleDictionary<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

    protected DoubleListNode<Entry<K, V>> head;

    protected DoubleListNode<Entry<K, V>> tail;

    private int currentSize;

    private final Comparator<K> comparator;

    public OrderedDoubleDictionary() {
        head = null;
        tail = null;
        currentSize = 0;
        comparator = null;
    }

    public OrderedDoubleDictionary(Comparator<K> comparator) {
        head = null;
        tail = null;
        currentSize = 0;
        this.comparator = comparator;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public V find(K key) {
        DoubleListNode<Entry<K, V>> node = findNode(key);
        return node == null ? null : node.getElement().getValue();
    }

    private DoubleListNode<Entry<K, V>> findNode(K key) {
        DoubleListNode<Entry<K, V>> node = head;
        int compareResult = 0;
        while (node != null && (compareResult = compare(node.getElement().getKey(), key)) <= 0) {
            K currentKey = node.getElement().getKey();

            if (compareResult == 0)
                return node;
            node = node.getNext();
        }
        return null;
    }

    @Override
    public V insert(K key, V value) {
        DoubleListNode<Entry<K, V>> node = head;
        DoubleListNode<Entry<K, V>> next = null;
        DoubleListNode<Entry<K, V>> previous = null;
        while (node != null) {
            K currentKey = node.getElement().getKey();

            int compareResult = compare(currentKey, key);

            if (compareResult == 0) {
                V oldValue = node.getElement().getValue();
                ((EntryClass<K, V>) node.getElement()).setValue(value);
                return oldValue;
            }
            if (compareResult > 0) {
                next = node;
                previous = node.getPrevious();
                break;
            }
            node = node.getNext();
        }

        DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<>(new EntryClass<>(key, value));
        addNode(newNode, next, previous);

        return null;
    }

    private void addNode(DoubleListNode<Entry<K, V>> newNode, DoubleListNode<Entry<K, V>> next,
                         DoubleListNode<Entry<K, V>> previous) {
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

    @Override
    public V remove(K key) {
        DoubleListNode<Entry<K, V>> node = findNode(key);
        if (node != null) {
            remove(node);
            return node.getElement().getValue();
        }
        return null;
    }

    private void remove(DoubleListNode<Entry<K, V>> node) {
        DoubleListNode<Entry<K, V>> next = node.getNext();
        DoubleListNode<Entry<K, V>> previous = node.getPrevious();

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

    @Override
    public Entry<K, V> minEntry() throws EmptyDictionaryException {
        return head.getElement();
    }

    @Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
        return tail.getElement();
    }

    private int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2) : k1.compareTo(k2);
    }

    @Override
    public Iterator<Entry<K, V>> iteratorEntries() {
        return new IteratorEntries();
    }

    @Override
    public Iterator<V> iteratorValues() {
        return new IteratorValues();
    }

    @Override
    public Iterator<K> iteratorKeys() {
        return new IteratorKeys();
    }

    abstract class ODLIterator {
        /**
         * Serial Version UID of the Class
         */
        static final long serialVersionUID = 0L;

        /**
         * Node with the next element in the iteration.
         */
        protected DoubleListNode<Entry<K, V>> nextToReturn;

        /**
         * Node with the previous element in the iteration.
         */
        protected DoubleListNode<Entry<K, V>> prevToReturn;

        public ODLIterator() {
            this.rewind();
        }

        public void rewind() {
            nextToReturn = head;
            prevToReturn = null;
        }

        public void fullForward() {
            prevToReturn = tail;
            nextToReturn = null;
        }

        public boolean hasNext() {
            return nextToReturn != null;
        }

        public boolean hasPrevious() {
            return prevToReturn != null;
        }

        private Entry<K, V> next() throws NoSuchElementException {
            if (!this.hasNext())
                throw new NoSuchElementException();

            Entry<K, V> element = nextToReturn.getElement();
            prevToReturn = nextToReturn.getPrevious();
            nextToReturn = nextToReturn.getNext();
            return element;
        }

        private Entry<K, V> previous() throws NoSuchElementException {
            if (!this.hasPrevious())
                throw new NoSuchElementException();

            Entry<K, V> element = prevToReturn.getElement();
            nextToReturn = prevToReturn.getNext();
            prevToReturn = prevToReturn.getPrevious();
            return element;
        }
    }

    class IteratorEntries extends ODLIterator implements TwoWayIterator<Entry<K, V>> {
        @Override
        public Entry<K, V> next() throws NoSuchElementException {
            return super.next();
        }

        @Override
        public Entry<K, V> previous() throws NoSuchElementException {
            return super.previous();
        }
    }

    class IteratorValues extends ODLIterator implements TwoWayIterator<V> {
        @Override
        public V next() throws NoSuchElementException {
            return super.next().getValue();
        }

        @Override
        public V previous() throws NoSuchElementException {
            return super.previous().getValue();
        }
    }

    class IteratorKeys extends ODLIterator implements TwoWayIterator<K> {
        @Override
        public K next() throws NoSuchElementException {
            return super.next().getKey();
        }

        @Override
        public K previous() throws NoSuchElementException {
            return super.previous().getKey();
        }
    }
}
