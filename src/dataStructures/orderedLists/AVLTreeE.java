package dataStructures.orderedLists;

import dataStructures.Comparator;
import dataStructures.Stack;
import dataStructures.StackInList;

public class AVLTreeE<E extends Comparable<E>> extends AdvancedBSTTreeE<E> {

    public AVLTreeE() {
        super();
    }

    public AVLTreeE(Comparator<E> comparator) {
        super(comparator);
    }

    // If there is an entry in the dictionary whose key is the specified key,
// replaces its value by the specified value and returns the old value;
// otherwise, inserts the entry (key, value) and returns null.
    public E insert(E element) {
        Stack<PathStepE<E>> path = new StackInList<>();
        BSTNodeE<E> node = this.findNode(element, path);
        if (node == null) {
            AVLNodeE<E> newLeaf = new AVLNodeE<E>(element);
            this.linkSubtree(newLeaf, path.top());
            currentSize++;
            this.reorganizeIns(path);
            return null;
        } else {
            E oldValue = node.getElement();
            node.setElement(element);
            return oldValue;
        }
    }

    // Every ancestor of the new leaf is stored in the stack,
// which is not empty.
    protected void reorganizeIns(Stack<PathStepE<E>> path) {
        boolean grew = true;
        PathStepE<E> lastStep = path.pop();
        AVLNodeE<E> parent = (AVLNodeE<E>) lastStep.parent;
        while (grew && parent != null) {
            if (lastStep.isLeftChild) // parent's left subtree has grown.
                switch (parent.getBalance()) {
                    case 'L':
                        this.rebalanceInsLeft(parent, path);
                        grew = false;
                        break;
                    case 'E':
                        parent.setBalance('L');
                        break;
                    case 'R':
                        parent.setBalance('E');
                        grew = false;
                        break;
                }
            else // parent's right subtree has grown.
                switch (parent.getBalance()) {
                    case 'L':
                        parent.setBalance('E');
                        grew = false;
                        break;
                    case 'E':
                        parent.setBalance('R');
                        break;
                    case 'R':
                        this.rebalanceInsRight(parent, path);
                        grew = false;
                        break;
                }
            lastStep = path.pop();
            parent = (AVLNodeE<E>) lastStep.parent;
        }
    }

    private void reorganizeRem(Stack<PathStepE<E>> path) {
        boolean shrunk = true;
        PathStepE<E> lastStep = path.pop();
        AVLNodeE<E> parent = (AVLNodeE<E>) lastStep.parent;
        while (shrunk && parent != null) {
            if (lastStep.isLeftChild) {             // parent's left subtree has shrunk.
                switch (parent.getBalance()) {
                    case 'L':
                        parent.setBalance('E');
                        break;
                    case 'E':
                        parent.setBalance('R');
                        shrunk = false;
                        break;
                    case 'R':
                        this.rebalanceRemLeft(parent, path);
                        shrunk = false;
                        break;
                }
            } else
                switch (parent.getBalance()) {
                    case 'L':
                        this.rebalanceRemRight(parent, path);
                        shrunk = false;
                        break;
                    case 'E':
                        parent.setBalance('L');
                        shrunk = false;
                        break;
                    case 'R':
                        parent.setBalance('E');
                        break;
                }
            lastStep = path.pop();
            parent = (AVLNodeE<E>) lastStep.parent;
        }
    }

    protected void rebalanceRemRight(AVLNodeE<E> node,
                                     Stack<PathStepE<E>> path) {
        AVLNodeE<E> leftChild = (AVLNodeE<E>) node.getLeft();
        switch (leftChild.getBalance()) {
            case 'L':
                this.rotateLeft1L(node, leftChild, path);
                break;
            case 'E':
                this.rotateLeft1E(node, leftChild, path);
                break;
            case 'R':
                this.rotateLeft2(node, leftChild, path);
                break;
        }
    }

    protected void rebalanceRemLeft(AVLNodeE<E> node,
                                    Stack<PathStepE<E>> path) {
        AVLNodeE<E> rightChild = (AVLNodeE<E>) node.getRight();
        switch (rightChild.getBalance()) {
            case 'L':
                this.rotateRight2(node, rightChild, path);
                break;
            case 'E':
                this.rotateRight1E(node, rightChild, path);
                break;
            case 'R':
                this.rotateRight1R(node, rightChild, path);
                break;
        }
    }

    // Every ancestor of node is stored in the stack, which is not empty.
// height( node.getLeft() ) - height( node.getRight() ) = 2.
    protected void rebalanceInsLeft(AVLNodeE<E> node,
                                    Stack<PathStepE<E>> path) {
        AVLNodeE<E> leftChild = (AVLNodeE<E>) node.getLeft();
        switch (leftChild.getBalance()) {
            case 'L':
                this.rotateLeft1L(node, leftChild, path);
                break;
            // case 'E':
            // Impossible.
            case 'R':
                this.rotateLeft2(node, leftChild, path);
                break;
        }
    }

    // Performs a single left rotation rooted at theRoot,
// when the balance factor of its leftChild is 'L'.
// Every ancestor of theRoot is stored in the stack, which is not empty.
// height( node.getLeft() ) - height( node.getRight() ) = 2.
    protected void rotateLeft1L(AVLNodeE<E> theRoot, AVLNodeE<E> leftChild,
                                Stack<PathStepE<E>> path) {
        theRoot.setBalance('E');
        leftChild.setBalance('E');
        this.rotateLeft(theRoot, leftChild, path);
    }

    /**
     * Performs a single left rotation rooted at theRoot,
     * when the balance factor of its leftChild is 'E'.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getLeft() ) - height( node.getRight() ) = 2.
     *
     * @param theRoot   - root of the rotation
     * @param leftChild - Left child of theRoot
     * @param path      - Stack of PathStep objects containing all ancestors of theRoot
     */
    protected void rotateLeft1E(AVLNodeE<E> theRoot, AVLNodeE<E> leftChild,
                                Stack<PathStepE<E>> path) {
        theRoot.setBalance('L');
        leftChild.setBalance('R');
        this.rotateLeft(theRoot, leftChild, path);
    }


    // Performs a double left rotation rooted at theRoot.
// Every ancestor of theRoot is stored in the stack, which is not empty.
// height( node.getLeft() ) - height( node.getRight() ) = 2.
    protected void rotateLeft2(AVLNodeE<E> theRoot, AVLNodeE<E> leftChild,
                               Stack<PathStepE<E>> path) {
        AVLNodeE<E> rightGrandchild = (AVLNodeE<E>) leftChild.getRight();
        switch (rightGrandchild.getBalance()) {
            case 'L':
                leftChild.setBalance('E');
                theRoot.setBalance('R');
                break;
            case 'E':
                leftChild.setBalance('E');
                theRoot.setBalance('E');
                break;
            case 'R':
                leftChild.setBalance('L');
                theRoot.setBalance('E');
                break;
        }
        rightGrandchild.setBalance('E');
        this.rotateLeft(theRoot, leftChild, rightGrandchild, path);
    }

    // Every ancestor of node is stored in the stack, which is not empty.
// height( node.getRight() ) - height( node.getLeft() ) = 2.
    protected void rebalanceInsRight(AVLNodeE<E> node,
                                     Stack<PathStepE<E>> path) {
        AVLNodeE<E> rightChild = (AVLNodeE<E>) node.getRight();
        switch (rightChild.getBalance()) {
            case 'L':
                this.rotateRight2(node, rightChild, path);
                break;
            // case 'E':
            // Impossible.
            case 'R':
                this.rotateRight1R(node, rightChild, path);
                break;
        }
    }

    // Performs a single right rotation rooted at theRoot,
// when the balance factor of its rightChild is 'R'.
// Every ancestor of theRoot is stored in the stack, which is not empty.
// height( node.getRight() ) - height( node.getLeft() ) = 2.
    protected void rotateRight1R(AVLNodeE<E> theRoot,
                                 AVLNodeE<E> rightChild, Stack<PathStepE<E>> path) {
        theRoot.setBalance('E');
        rightChild.setBalance('E');
        this.rotateRight(theRoot, rightChild, path);
    }

    /**
     * Performs a single right rotation rooted at theRoot,
     * when the balance factor of its rightChild is 'E'.
     * Every ancestor of theRoot is stored in the stack, which is not empty.
     * height( node.getRight() ) - height( node.getLeft() ) = 2.
     *
     * @param theRoot    - root of the rotation
     * @param rightChild - Right child of theRoot
     * @param path       - Stack of PathStep objects containing all ancestors of theRoot
     */
    protected void rotateRight1E(AVLNodeE<E> theRoot,
                                 AVLNodeE<E> rightChild, Stack<PathStepE<E>> path) {
        theRoot.setBalance('R');
        rightChild.setBalance('L');
        this.rotateRight(theRoot, rightChild, path);
    }


    // Performs a double right rotation rooted at theRoot.
// Every ancestor of theRoot is stored in the stack, which is not empty.
// height( node.getRight() ) - height( node.getLeft() ) = 2.
    protected void rotateRight2(AVLNodeE<E> theRoot,
                                AVLNodeE<E> rightChild, Stack<PathStepE<E>> path) {
        AVLNodeE<E> leftGrandchild = (AVLNodeE<E>) rightChild.getLeft();
        switch (leftGrandchild.getBalance()) {
            case 'L':
                theRoot.setBalance('E');
                rightChild.setBalance('R');
                break;
            case 'E':
                theRoot.setBalance('E');
                rightChild.setBalance('E');
                break;
            case 'R':
                theRoot.setBalance('L');
                rightChild.setBalance('E');
                break;
        }
        leftGrandchild.setBalance('E');
        this.rotateRight(theRoot, rightChild, leftGrandchild, path);
    }

    // Returns the node with the smallest key
// in the tree rooted at the specified node.
// Moreover, stores the path into the stack.
// Requires: theRoot != null.
    protected BSTNodeE<E> minNode(BSTNodeE<E> theRoot,
                                  Stack<PathStepE<E>> path) {
        BSTNodeE<E> node = theRoot;
        while (node.getLeft() != null) {
            path.push(new PathStepE<E>(node, true));
            node = node.getLeft();
        }
        return node;
    }

    // If there is an entry in the dictionary whose key is the specified key,
// removes it from the dictionary and returns its value;
// otherwise, returns null.
    public E remove(E element) {
        Stack<PathStepE<E>> path = new StackInList<PathStepE<E>>();
        BSTNodeE<E> node = this.findNode(element, path);
        if (node == null)
            return null;
        else {
            E oldValue = node.getElement();
            if (node.getLeft() == null)   // The left subtree is empty.
                this.linkSubtree(node.getRight(), path.top());
            else if (node.getRight() == null) // The right subtree is empty.
                this.linkSubtree(node.getLeft(), path.top());
            else {
                // Node has 2 children. Replace the node's entry with
                // the 'minEntry' of the right subtree.
                path.push(new PathStepE<E>(node, false));
                BSTNodeE<E> minNode = this.minNode(node.getRight(), path);
                node.setElement(minNode.getElement());
                // Remove the 'minEntry' of the right subtree.
                this.linkSubtree(minNode.getRight(), path.top());
            }
            currentSize--;
            this.reorganizeRem(path);
            return oldValue;
        }
    }
}
