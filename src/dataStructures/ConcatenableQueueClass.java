package dataStructures;

public class ConcatenableQueueClass<E> extends QueueInList<E> implements ConcatenableQueue<E> {

    public void append(ConcatenableQueue<E> queue) {
        if (queue instanceof ConcatenableQueueClass<E> && ((ConcatenableQueueClass<E>) queue).list instanceof DoubleList) {
            ((DoubleList<E>) list).append((DoubleList<E>) ((ConcatenableQueueClass<E>) queue).list);
        } else
            while (!queue.isEmpty()) {
                this.enqueue(queue.dequeue());
            }
    }
}
