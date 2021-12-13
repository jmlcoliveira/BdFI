package dataStructures.orderedLists;

import dataStructures.Comparator;
import dataStructures.Iterator;
import dataStructures.orderedDictionaries.BinarySearchTree;

public class BinarySearchTreeE<E extends Comparable<E>> extends BinarySearchTree<E, E> implements OrderedList<E> {

    BinarySearchTreeE() {
        super();
    }

    BinarySearchTreeE(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    public E insert(E element) {
        return insert(element, element);
    }

    @Override
    public E minElement() {
        return minEntry().getValue();
    }

    @Override
    public E maxElement() {
        return maxEntry().getValue();
    }

    @Override
    public Iterator<E> iterator() {
        return iteratorValues();
    }
}
