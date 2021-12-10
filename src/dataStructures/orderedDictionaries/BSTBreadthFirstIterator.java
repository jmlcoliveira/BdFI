package dataStructures.orderedDictionaries;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.Queue;
import dataStructures.QueueInList;
import dataStructures.exceptions.NoSuchElementException;

public class BSTBreadthFirstIterator<K extends Comparable<K>, V> implements Iterator<Entry<K,V>> {

    Queue<BSTNode<K,V>> queue;
    BSTNode<K,V> root;

    public BSTBreadthFirstIterator(BSTNode<K,V> root) {
           queue = new QueueInList<>();
           this.root = root;
           rewind();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        BSTNode<K,V> node = queue.dequeue();
        if(node.getLeft() != null)
            queue.enqueue(node.getLeft());
        if(node.getRight() != null)
            queue.enqueue(node.getRight());
        return node.getEntry();
    }

    @Override
    public void rewind() {
        queue.enqueue(root);
    }
}
