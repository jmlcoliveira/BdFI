package dataStructures;

public class AdvancedBSTTree<K extends Comparable<K>, V>
        extends BinarySearchTree<K,V> {


    protected BSTNode<K,V> minNode( BSTNode<K,V> theRoot,
                                    Stack<PathStep<K,V>> path ){
        BSTNode<K,V> node = theRoot;
        while ( node.getLeft() != null ){
            path.push( new PathStep<K,V>(node, true) );
            node = node.getLeft();
        }
        return node;
    }

    protected BSTNode<K,V> findNode( K key, Stack<PathStep<K,V>> path )
    {
        path.push( new PathStep<K,V>(null, false) );
        BSTNode<K,V> node = root;
        while ( node != null )
        {
            int compResult = compare(key, node.getKey());
            if ( compResult == 0 )
                return node;
            else if ( compResult < 0 )
            {
                path.push( new PathStep<K,V>(node, true) );
                node = node.getLeft();
            }
            else
            {
                path.push( new PathStep<K,V>(node, false) );
                node = node.getRight();
            }
        }
        return null;
    }

    protected void rotateRight( BSTNode<K,V> theRoot,
                                BSTNode<K,V> rightChild, Stack<PathStep<K,V>> path ){
        theRoot.setRight( rightChild.getLeft() );
        rightChild.setLeft(theRoot);
        this.linkSubtree(rightChild, path.top());
    }

    protected void rotateRight( BSTNode<K,V> theRoot,
                                BSTNode<K,V> rightChild, BSTNode<K,V> leftGrandchild,
                                Stack<PathStep<K,V>> path ){
        theRoot.setRight( leftGrandchild.getLeft() );
        rightChild.setLeft( leftGrandchild.getRight() );
        leftGrandchild.setLeft(theRoot);
        leftGrandchild.setRight(rightChild);
        this.linkSubtree(leftGrandchild, path.top());
    }

    protected void rotateLeft( BSTNode<K,V> theRoot,
                               BSTNode<K,V> leftChild, Stack<PathStep<K,V>> path ){
        theRoot.setLeft( leftChild.getRight() );
        leftChild.setRight(theRoot);
        this.linkSubtree(leftChild, path.top());
    }

    protected void rotateLeft( BSTNode<K,V> theRoot,
                               BSTNode<K,V> leftChild, BSTNode<K,V> rightGrandchild,
                               Stack<PathStep<K,V>> path ) {
        leftChild.setRight( rightGrandchild.getLeft() );
        theRoot.setLeft( rightGrandchild.getRight() );
        rightGrandchild.setLeft(leftChild);
        rightGrandchild.setRight(theRoot);
        this.linkSubtree(rightGrandchild, path.top());
    }
}
