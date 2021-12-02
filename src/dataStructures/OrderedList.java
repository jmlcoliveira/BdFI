package dataStructures;

import java.io.Serializable;

public interface OrderedList<E extends Comparable<E>> extends Serializable {

    boolean isEmpty();

    int size();

    E find(E element);

    E insert(E element);

    E remove(E element);

    Iterator<E> iterator();
}
