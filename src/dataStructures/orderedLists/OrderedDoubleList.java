package dataStructures.orderedLists;

import dataStructures.Comparator;
import dataStructures.Iterator;
import dataStructures.orderedDictionaries.OrderedDoubleDictionary;

public class OrderedDoubleList<E extends Comparable<E>> extends OrderedDoubleDictionary<E, E> implements OrderedList<E> {

    public OrderedDoubleList() {
        super();
    }

    public OrderedDoubleList(Comparator<E> comparator) {
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
