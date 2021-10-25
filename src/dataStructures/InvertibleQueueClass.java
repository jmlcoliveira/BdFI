package dataStructures;

import dataStructures.exceptions.EmptyQueueException;

public class InvertibleQueueClass<E> extends QueueInList<E> implements InvertibleQueue<E> {

    private boolean inverted = false;

    @Override
    public void enqueue(E element) {
        if (inverted)
            list.addFirst(element);
        else
            super.enqueue(element);
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (list.isEmpty()) throw new EmptyQueueException();
        if (inverted)
            return list.removeLast();
        else
            return super.dequeue();
    }

    @Override
    public void invert() {
        inverted = !inverted;
    }
}
