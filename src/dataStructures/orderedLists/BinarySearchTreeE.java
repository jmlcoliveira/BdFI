package dataStructures.orderedLists;

import dataStructures.Comparator;
import dataStructures.Iterator;
import dataStructures.Stack;
import dataStructures.StackInList;
import dataStructures.exceptions.EmptyDictionaryException;
import dataStructures.exceptions.EmptyListException;
import dataStructures.exceptions.NoSuchElementException;

/**
 * BinarySearchTree implementation
 *
 * @author AED team
 * @version 1.0
 */
public class BinarySearchTreeE<E extends Comparable<E>>
        implements OrderedList<E> {

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;
    private final Comparator<E> comparator;
    /**
     * The root of the tree.
     */
    protected BSTNodeE<E> root;
    /**
     * Number of entries in the tree.
     */
    protected int currentSize;


    /**
     * Tree Constructor - creates an empty tree.
     */
    public BinarySearchTreeE() {
        root = null;
        currentSize = 0;
        comparator = null;
    }

    public BinarySearchTreeE(Comparator<E> comparator) {
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
    public E find(E element) {
        BSTNodeE<E> node = this.findNode(root, element);
        if (node == null)
            return null;
        else
            return node.getElement();
    }

    /**
     * Returns the node whose key is the specified key;
     * or null if no such node exists.
     *
     * @param node where the search starts
     * @return the found node, when the search is successful
     */
    protected BSTNodeE<E> findNode(BSTNodeE<E> node, E element) {
        if (node == null)
            return null;
        else {
            int compResult = compare(element, node.getElement());
            if (compResult == 0)
                return node;
            else if (compResult < 0)
                return this.findNode(node.getLeft(), element);
            else
                return this.findNode(node.getRight(), element);
        }
    }

    public E minElement() throws EmptyListException {
        if (this.isEmpty())
            throw new EmptyDictionaryException();

        return this.minNode(root).getElement();
    }

    /**
     * Returns the node with the smallest key
     * in the tree rooted at the specified node.
     * Requires: node != null.
     *
     * @param node - node that roots the tree
     * @return node with the smallest key in the tree
     */
    protected BSTNodeE<E> minNode(BSTNodeE<E> node) {
        if (node.getLeft() == null)
            return node;
        else
            return this.minNode(node.getLeft());
    }

    @Override
    public E maxElement() throws EmptyListException {
        if (this.isEmpty())
            throw new EmptyDictionaryException();

        return this.maxNode(root).getElement();
    }

    /**
     * Returns the node with the largest key
     * in the tree rooted at the specified node.
     * Requires: node != null.
     *
     * @param node that roots the tree
     * @return node with the largest key in the tree
     */
    protected BSTNodeE<E> maxNode(BSTNodeE<E> node) {
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
     * @param lastStep - PathStepE object referring to parent
     * @return the found node, when the search is successful
     */
    protected BSTNodeE<E> findNode(E element, PathStepE<E> lastStep) {
        BSTNodeE<E> node = root;
        while (node != null) {
            int compResult = compare(element, node.getElement());
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
    public E insert(E element) {
        PathStepE<E> lastStep = new PathStepE<E>(null, false);
        BSTNodeE<E> node = this.findNode(element, lastStep);
        if (node == null) {
            BSTNodeE<E> newLeaf = new BSTNodeE<E>(element);
            this.linkSubtree(newLeaf, lastStep);
            currentSize++;
            return null;
        } else {
            E oldValue = node.getElement();
            node.setElement(element);
            return oldValue;
        }
    }

    /**
     * Links a new subtree, rooted at the specified node, to the tree.
     * The parent of the old subtree is stored in lastStep.
     *
     * @param node     - root of the subtree
     * @param lastStep - PathStepE object referring to the parent of the old subtree
     */
    protected void linkSubtree(BSTNodeE<E> node, PathStepE<E> lastStep) {
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
     * @param lastStep - PathStepE object to refer to the parent of theRoot
     * @return node containing the entry with the minimum key
     */
    protected BSTNodeE<E> minNode(BSTNodeE<E> theRoot,
                                  PathStepE<E> lastStep) {
        BSTNodeE<E> node = theRoot;
        while (node.getLeft() != null) {
            lastStep.set(node, true);
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public E remove(E element) {
        PathStepE<E> lastStep = new PathStepE<>(null, false);
        BSTNodeE<E> node = this.findNode(element, lastStep);
        if (node == null)
            return null;
        else {
            E oldValue = node.getElement();
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
                BSTNodeE<E> minNode = this.minNode(node.getRight(), lastStep);
                node.setElement(minNode.getElement());
                // Remove the 'minEntry' of the right subtree.
                this.linkSubtree(minNode.getRight(), lastStep);
            }
            currentSize--;
            return oldValue;
        }
    }

    protected int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : e1.compareTo(e2);
    }

    /**
     * Returns an iterator of the entries in the dictionary
     * which preserves the key order relation.
     *
     * @return key-order iterator of the entries in the dictionary
     */
    public Iterator<E> iterator() {
        return new BSTKeyOrderIterator();
    }

    /**
     * Inner class to store path steps
     *
     * @author AED team
     * @version 1.0
     */
    protected static class PathStepE<E extends Comparable<E>> {

        /**
         * The parent of the node.
         */
        public BSTNodeE<E> parent;

        /**
         * The node is the left or the right child of parent.
         */
        public boolean isLeftChild;

        /**
         * PathStepE constructor
         *
         * @param theParent - ancestor of the current node
         * @param toTheLeft - will be true of the current node is the left child of theParent
         */
        public PathStepE(BSTNodeE<E> theParent, boolean toTheLeft) {
            parent = theParent;
            isLeftChild = toTheLeft;
        }


        /**
         * Method to set node parent before moving in the tree
         *
         * @param newParent - ancestor of the current node
         * @param toTheLeft - will be true of the current node is the left child of theParent
         */
        public void set(BSTNodeE<E> newParent, boolean toTheLeft) {
            parent = newParent;
            isLeftChild = toTheLeft;
        }

    }

    class BSTKeyOrderIterator implements Iterator<E> {

        private Stack<BSTNodeE<E>> stack;

        BSTKeyOrderIterator() {
            rewind();
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }


        public E next() throws NoSuchElementException {
            if (stack.isEmpty()) throw new NoSuchElementException();
            BSTNodeE<E> toReturn = findNext();
            return toReturn.getElement();
        }

        protected void findLowest(BSTNodeE<E> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        protected BSTNodeE<E> findNext() {
            BSTNodeE<E> toReturn = stack.pop();
            if (toReturn.getRight() != null)
                findLowest(toReturn.getRight());
            return toReturn;
        }

        public void rewind() {
            stack = new StackInList<>();
            findLowest(root);
        }
    }
}

