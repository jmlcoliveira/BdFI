package dataStructures.orderedLists;


import dataStructures.Comparator;
import dataStructures.Stack;

/**
 * Advanced BSTree Data Type implementation
 * @author AED team
 * @version 1.0
 * @param <E> Generic type Element, must extend comparable
 */
public class AdvancedBSTTreeE<E extends Comparable<E>>
        extends BinarySearchTreeE<E> {

    AdvancedBSTTreeE() {
        super();
    }

    AdvancedBSTTreeE(Comparator<E> comparator) {
        super(comparator);
    }

    protected BSTNodeE<E> minNode(BSTNodeE<E> theRoot,
                                  Stack<PathStepE<E>> path) {
        BSTNodeE<E> node = theRoot;
        while (node.getLeft() != null) {
            path.push(new PathStepE<E>(node, true));
            node = node.getLeft();
        }
        return node;
    }

    protected BSTNodeE<E> findNode(E element, Stack<PathStepE<E>> path) {
        path.push(new PathStepE<E>(null, false));
        BSTNodeE<E> node = root;
        while (node != null) {
            int compResult = compare(element, node.getElement());
            if (compResult == 0)
                return node;
            else if (compResult < 0) {
                path.push(new PathStepE<E>(node, true));
                node = node.getLeft();
            } else {
                path.push(new PathStepE<E>(node, false));
                node = node.getRight();
            }
        }
        return null;
    }

    protected void rotateRight(BSTNodeE<E> theRoot,
                               BSTNodeE<E> rightChild, Stack<PathStepE<E>> path) {
        theRoot.setRight(rightChild.getLeft());
        rightChild.setLeft(theRoot);
        this.linkSubtree(rightChild, path.top());
    }

    protected void rotateRight(BSTNodeE<E> theRoot,
                               BSTNodeE<E> rightChild, BSTNodeE<E> leftGrandchild,
                               Stack<PathStepE<E>> path) {
        theRoot.setRight(leftGrandchild.getLeft());
        rightChild.setLeft(leftGrandchild.getRight());
        leftGrandchild.setLeft(theRoot);
        leftGrandchild.setRight(rightChild);
        this.linkSubtree(leftGrandchild, path.top());
    }

    protected void rotateLeft(BSTNodeE<E> theRoot,
                              BSTNodeE<E> leftChild, Stack<PathStepE<E>> path) {
        theRoot.setLeft(leftChild.getRight());
        leftChild.setRight(theRoot);
        this.linkSubtree(leftChild, path.top());
    }

    protected void rotateLeft(BSTNodeE<E> theRoot,
                              BSTNodeE<E> leftChild, BSTNodeE<E> rightGrandchild,
                              Stack<PathStepE<E>> path) {
        leftChild.setRight(rightGrandchild.getLeft());
        theRoot.setLeft(rightGrandchild.getRight());
        rightGrandchild.setLeft(leftChild);
        rightGrandchild.setRight(theRoot);
        this.linkSubtree(rightGrandchild, path.top());
    }
}
