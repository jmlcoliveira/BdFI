package dataStructures;

import dataStructures.exceptions.EmptyDictionaryException;

public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V>{

    protected DoubleListNode<Entry<K,V>> head;

    protected DoubleListNode<Entry<K,V>> tail;

    private int currentSize;

    public OrderedDoubleList() {
        head = null;
        tail = null;
        currentSize = 0;
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
        DoubleListNode<Entry<K,V>> node = head;
        while(node != null) {
            K currentKey = node.getElement().getKey();

            if(currentKey.equals(key))
                return node.getElement().getValue();
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

            if (currentKey.equals(key)) {
                V oldValue = node.getElement().getValue();
                ((EntryClass<K,V>)node.getElement()).setValue(value);
                return oldValue;
            }
            if (node.getElement().getKey().compareTo(key) > 0) {
                next = node;
                previous = node.getPrevious();
                break;
            }
            node = node.getNext();
        }

        DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<Entry<K, V>>(new EntryClass<>(key, value));
        addNode(newNode, next, previous);

        return null;
    }

    private void addNode(DoubleListNode<Entry<K,V>> newNode,DoubleListNode<Entry<K,V>> next,
                    DoubleListNode<Entry<K,V>> previous) {
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
        DoubleListNode<Entry<K,V>> node = head;
        while(node != null) {
            K currentKey = node.getElement().getKey();

            if(currentKey.equals(key)) {
                V oldValue = node.getElement().getValue();
                remove(node);
                return oldValue;
            }
            node = node.getNext();
        }
        return null;
    }

    private void remove(DoubleListNode<Entry<K,V>> node) {
        DoubleListNode<Entry<K,V>> next = node.getNext();
        DoubleListNode<Entry<K,V>> previous = node.getPrevious();

        if(head == tail) {
            head = null;
            tail = null;
        } else if(node == head) {
            next.setPrevious(null);
            head = next;
        } else if(node == tail) {
            previous.setNext(null);
            tail = previous;
        } else {
            next.setPrevious(previous);
            previous.setNext(next);
        }
        currentSize--;

    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new DoubleListIterator<Entry<K,V>>(head, tail);
    }

    @Override
    public Entry<K, V> minEntry() throws EmptyDictionaryException {
        return head.getElement();
    }

    @Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
        return tail.getElement();
    }
}
