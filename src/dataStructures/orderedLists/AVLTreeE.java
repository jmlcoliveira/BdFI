package dataStructures.orderedLists;

import dataStructures.Comparator;
import dataStructures.Iterator;
import dataStructures.orderedDictionaries.AVLTree;

public class AVLTreeE<E extends Comparable<E>> extends AVLTree<E, E> implements OrderedList<E> {

    public AVLTreeE() {
        super();
    }

    public AVLTreeE(Comparator<E> comparator) {
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
