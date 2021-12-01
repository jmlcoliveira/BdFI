package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

public class BSTKeyOrderIterator<K extends Comparable<K>, V> implements Iterator<Entry<K,V>>{

    Stack<BSTNode<K,V>> stack;
    BSTNode<K,V> root;

    public BSTKeyOrderIterator(BSTNode<K,V> root) {
        this.root = root;
        stack = new StackInList<>();
        rewind();
    }
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Entry<K,V> next() throws NoSuchElementException {
        if(stack.isEmpty())
            throw new NoSuchElementException();
        BSTNode<K,V> node = stack.pop();
        BSTNode<K,V> retNode = node;

        if(node.getRight() != null) {
            node = node.getRight();
            stack.push(node);
            while (node.getLeft() != null) {
                node = node.getLeft();
                stack.push(node);
            }
        }

        return retNode.getEntry();
    }

    @Override
    public void rewind() {
        BSTNode<K,V> node = root;
        while(node != null) {
            stack.push(node);
            node = node.getLeft();
        }
    }
}
