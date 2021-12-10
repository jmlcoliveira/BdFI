package dataStructures.orderedLists;

import dataStructures.Iterator;

import java.io.Serializable;

public interface OrderedList<E extends Comparable<E>> extends Serializable {

    boolean isEmpty();

    int size();

    E find(E element);

    E insert(E element);

    E remove(E element);

    E minElement();

    E maxElement();

    Iterator<E> iterator();
}
