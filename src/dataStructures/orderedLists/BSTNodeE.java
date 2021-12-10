package dataStructures.orderedLists;

import java.io.Serializable;

/**
 * BST node implementation
 *
 * @author AED team
 * @version 1.0
 */
class BSTNodeE<E extends Comparable<E>> implements Serializable {

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;


    /**
     * Entry stored in the node.
     */
    private E element;

    /**
     * (Pointer to) the left child.
     */
    private BSTNodeE<E> leftChild;

    /**
     * (Pointer to) the right child.
     */
    private BSTNodeE<E> rightChild;


    /**
     * Constructor for BST nodes
     *
     * @param left  sub-tree of this node
     * @param right sub-tree of this node
     */
    public BSTNodeE(E element, BSTNodeE<E> left, BSTNodeE<E> right) {
        this.element = element;
        leftChild = left;
        rightChild = right;
    }


    /**
     * Constructor for BST nodes
     */
    public BSTNodeE(E element) {
        this(element, null, null);
    }


    /**
     * Returns the entry (key and value) of the current node.
     *
     * @return
     */
    public E getElement() {
        return element;
    }

    /**
     * Assigns a new entry (key and value) to the current BST node
     */
    public void setElement(E newElement) {
        element = newElement;
    }

    /**
     * /**
     * Returns the left child node of the current node.
     *
     * @return
     */
    public BSTNodeE<E> getLeft() {
        return leftChild;
    }

    /**
     * Sets the new left child node of the this node
     *
     * @param newLeft the new left child node of the current node
     */
    public void setLeft(BSTNodeE<E> newLeft) {
        leftChild = newLeft;
    }

    /**
     * Returns the right child node of the current node.
     *
     * @return
     */
    public BSTNodeE<E> getRight() {
        return rightChild;
    }

    /**
     * Sets the new right child node of the this node
     *
     * @param newRight the new right child node of the current node
     */
    public void setRight(BSTNodeE<E> newRight) {
        rightChild = newRight;
    }


    /**
     * Returns true iff the node is a leaf.
     *
     * @return
     */
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}
