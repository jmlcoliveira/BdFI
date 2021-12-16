package dataStructures.orderedDictionaries;

import dataStructures.*;
import dataStructures.exceptions.EmptyDictionaryException;
import dataStructures.exceptions.NoSuchElementException;

/**
 * BinarySearchTree implementation
 *
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 * @author AED team
 * @version 1.0
 */
public class BinarySearchTree<K extends Comparable<K>, V>
        implements OrderedDictionary<K, V> {

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;


    /**
     * The root of the tree.
     */
    protected BSTNode<K, V> root;

    /**
     * Number of entries in the tree.
     */
    protected int currentSize;

    private final Comparator<K> comparator;


    /**
     * Tree Constructor - creates an empty tree.
     */
    public BinarySearchTree() {
        root = null;
        currentSize = 0;
        comparator = null;
    }

    public BinarySearchTree(Comparator<K> comparator) {
        root = null;
        currentSize = 0;
        this.comparator = comparator;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public V find(K key) {
        BSTNode<K, V> node = this.findNode(root, key);
        if (node == null)
            return null;
        else
            return node.getValue();
    }

    /**
     * Returns the node whose key is the specified key;
     * or null if no such node exists.
     *
     * @param node where the search starts
     * @param key  to be found
     * @return the found node, when the search is successful
     */
    protected BSTNode<K, V> findNode(BSTNode<K, V> node, K key) {
        if (node == null)
            return null;
        else {
            int compResult = compare(key, node.getKey());
            if (compResult == 0)
                return node;
            else if (compResult < 0)
                return this.findNode(node.getLeft(), key);
            else
                return this.findNode(node.getRight(), key);
        }
    }

    @Override
    public Entry<K, V> minEntry() throws EmptyDictionaryException {
        if (this.isEmpty())
            throw new EmptyDictionaryException();

        return this.minNode(root).getEntry();
    }

    /**
     * Returns the node with the smallest key
     * in the tree rooted at the specified node.
     * Requires: node != null.
     *
     * @param node - node that roots the tree
     * @return node with the smallest key in the tree
     */
    protected BSTNode<K, V> minNode(BSTNode<K, V> node) {
        if (node.getLeft() == null)
            return node;
        else
            return this.minNode(node.getLeft());
    }

    @Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
        if (this.isEmpty())
            throw new EmptyDictionaryException();

        return this.maxNode(root).getEntry();
    }

    /**
     * Returns the node with the largest key
     * in the tree rooted at the specified node.
     * Requires: node != null.
     *
     * @param node that roots the tree
     * @return node with the largest key in the tree
     */
    protected BSTNode<K, V> maxNode(BSTNode<K, V> node) {
        if (node.getRight() == null)
            return node;
        else
            return this.maxNode(node.getRight());
    }

    /**
     * Returns the node whose key is the specified key;
     * or null if no such node exists.
     * Moreover, stores the last step of the path in lastStep.
     *
     * @param key      to be searched
     * @param lastStep - PathStep object referring to parent
     * @return the found node, when the search is successful
     */
    protected BSTNode<K, V> findNode(K key, PathStep<K, V> lastStep) {
        BSTNode<K, V> node = root;
        while (node != null) {
            int compResult = compare(key, node.getKey());
            if (compResult == 0)
                return node;
            else if (compResult < 0) {
                lastStep.set(node, true);
                node = node.getLeft();
            } else {
                lastStep.set(node, false);
                node = node.getRight();
            }
        }
        return null;
    }

    @Override
    public V insert(K key, V value) {
        PathStep<K, V> lastStep = new PathStep<K, V>(null, false);
        BSTNode<K, V> node = this.findNode(key, lastStep);
        if (node == null) {
            BSTNode<K, V> newLeaf = new BSTNode<K, V>(key, value);
            this.linkSubtree(newLeaf, lastStep);
            currentSize++;
            return null;
        } else {
            V oldValue = node.getValue();
            node.setValue(value);
            return oldValue;
        }
    }

    /**
     * Links a new subtree, rooted at the specified node, to the tree.
     * The parent of the old subtree is stored in lastStep.
     *
     * @param node     - root of the subtree
     * @param lastStep - PathStep object referring to the parent of the old subtree
     */
    protected void linkSubtree(BSTNode<K, V> node, PathStep<K, V> lastStep) {
        if (lastStep.parent == null)
            // Change the root of the tree.
            root = node;
        else
            // Change a child of parent.
            if (lastStep.isLeftChild)
                lastStep.parent.setLeft(node);
            else
                lastStep.parent.setRight(node);
    }

    /**
     * Returns the node with the smallest key
     * in the tree rooted at the specified node.
     * Moreover, stores the last step of the path in lastStep.
     * Requires: theRoot != null.
     *
     * @param theRoot  - node that roots the tree
     * @param lastStep - Pathstep object to refer to the parent of theRoot
     * @return node containing the entry with the minimum key
     */
    protected BSTNode<K, V> minNode(BSTNode<K, V> theRoot,
                                    PathStep<K, V> lastStep) {
        BSTNode<K, V> node = theRoot;
        while (node.getLeft() != null) {
            lastStep.set(node, true);
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public V remove(K key) {
        PathStep<K, V> lastStep = new PathStep<K, V>(null, false);
        BSTNode<K, V> node = this.findNode(key, lastStep);
        if (node == null)
            return null;
        else {
            V oldValue = node.getValue();
            if (node.getLeft() == null)
                // The left subtree is empty.
                this.linkSubtree(node.getRight(), lastStep);
            else if (node.getRight() == null)
                // The right subtree is empty.
                this.linkSubtree(node.getLeft(), lastStep);
            else {
                // Node has 2 children. Replace the node's entry with
                // the 'minEntry' of the right subtree.
                lastStep.set(node, false);
                BSTNode<K, V> minNode = this.minNode(node.getRight(), lastStep);
                node.setEntry(minNode.getEntry());
                // Remove the 'minEntry' of the right subtree.
                this.linkSubtree(minNode.getRight(), lastStep);
            }
            currentSize--;
            return oldValue;
        }
    }

    protected int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2) : k1.compareTo(k2);
    }

    /**
     * Returns an iterator of the entries in the dictionary
     * which preserves the key order relation.
     *
     * @return key-order iterator of the entries in the dictionary
     */
    public Iterator<Entry<K, V>> iteratorEntries() {
        return new IteratorEntry();
    }

    public Iterator<V> iteratorValues() {
        return new IteratorValues();
    }

    @Override
    public Iterator<K> iteratorKeys() {
        return new IteratorKeys();
    }

    abstract class BSTKeyOrderIterator {

        private Stack<BSTNode<K, V>> stack;

        BSTKeyOrderIterator() {
            rewind();
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }


        private Entry<K, V> next() throws NoSuchElementException {
            if (stack.isEmpty()) throw new NoSuchElementException();
            BSTNode<K, V> toReturn = findNext();
            return toReturn.getEntry();
        }

        protected void findLowest(BSTNode<K, V> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        protected BSTNode<K, V> findNext() {
            BSTNode<K, V> toReturn = stack.pop();
            if (toReturn.getRight() != null)
                findLowest(toReturn.getRight());
            return toReturn;
        }

        public void rewind() {
            stack = new StackInList<>();
            findLowest(root);
        }
    }

    class IteratorEntry extends BSTKeyOrderIterator implements Iterator<Entry<K, V>> {
        @Override
        public Entry<K, V> next() throws NoSuchElementException {
            return super.next();
        }
    }

    class IteratorValues extends BSTKeyOrderIterator implements Iterator<V> {
        @Override
        public V next() throws NoSuchElementException {
            return super.next().getValue();
        }
    }

    /**
     * Inner class to store path steps
     *
     * @param <K> Generic type Key, must extend comparable
     * @param <V> Generic type Value
     * @author AED team
     * @version 1.0
     */
    protected static class PathStep<K extends Comparable<K>, V> {

        /**
         * The parent of the node.
         */
        public BSTNode<K, V> parent;

        /**
         * The node is the left or the right child of parent.
         */
        public boolean isLeftChild;

        /**
         * PathStep constructor
         *
         * @param theParent - ancestor of the current node
         * @param toTheLeft - will be true of the current node is the left child of theParent
         */
        public PathStep(BSTNode<K, V> theParent, boolean toTheLeft) {
            parent = theParent;
            isLeftChild = toTheLeft;
        }


        /**
         * Method to set node parent before moving in the tree
         *
         * @param newParent - ancestor of the current node
         * @param toTheLeft - will be true of the current node is the left child of theParent
         */
        public void set(BSTNode<K, V> newParent, boolean toTheLeft) {
            parent = newParent;
            isLeftChild = toTheLeft;
        }

    }

    class IteratorKeys extends BSTKeyOrderIterator implements Iterator<K> {
        @Override
        public K next() throws NoSuchElementException {
            return super.next().getKey();
        }
    }

    public Iterator<Entry<K, V>> breadthIterator() {
        return new BSTBreadthFirstIterator<>(root);
    }
}

