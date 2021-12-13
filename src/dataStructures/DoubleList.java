package dataStructures;

import dataStructures.exceptions.EmptyListException;
import dataStructures.exceptions.InvalidPositionException;

/**
 * Adaptation of the DoubleList class given by the AED Team
 *
 * @param <E> Generic Element
 * @author Guilherme Pocas (60236) g.pocas@campus.fct.unl.pt
 * @author Joao Oliveira (61052) jml.oliveira@campus.fct.unl.pt
 */
public class DoubleList<E> implements List<E> {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    // Node at the head of the list.
    protected DoubleListNode<E> head;
    // Node at the tail of the list.
    protected DoubleListNode<E> tail;
    // Number of elements in the list.
    protected int currentSize;

    public DoubleList() {
        head = null;
        tail = null;
        currentSize = 0;
    }

    public DoubleList(DoubleList<E> list) {
        head = list.head;
        tail = list.tail;
        currentSize = list.currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public Iterator<E> iterator() {
        return new DoubleListIterator<E>(head, tail);
    }

    @Override
    public E getFirst() throws EmptyListException {
        if (currentSize == 0 || head == null)
            throw new EmptyListException();
        return head.getElement();
    }

    @Override
    public E getLast() throws EmptyListException {
        if (currentSize == 0 || head == null)
            throw new EmptyListException();
        return tail.getElement();
    }

    @Override
    public E get(int position) throws InvalidPositionException {
        if (position < 0 || position >= currentSize)
            throw new InvalidPositionException();
        return getNode(position).getElement();
    }

    protected DoubleListNode<E> getNode(int position) {
        DoubleListNode<E> node = head;
        if (position <= (currentSize - 1) / 2) {
            for (int i = 0; i < position; i++)
                node = node.getNext();
        } else {
            node = tail;
            for (int i = currentSize - 1; i > position; i--)
                node = node.getPrevious();
        }
        return node;
    }

    @Override
    public void addFirst(E element) {
        DoubleListNode<E> newNode = new DoubleListNode<>(element, null, head);
        if (this.isEmpty())
            tail = newNode;
        else
            head.setPrevious(newNode);
        newNode.setNext(head);
        head = newNode;
        currentSize++;
    }

    @Override
    public void addLast(E element) {
        if (this.isEmpty())
            addFirst(element);
        else {
            DoubleListNode<E> newNode = new DoubleListNode<>(element, tail, null);
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
            currentSize++;
        }
    }

    @Override
    public void add(int position, E element) throws InvalidPositionException {
        if (position < 0 || position > currentSize)
            throw new InvalidPositionException();
        if (position == 0)
            addFirst(element);
        else if (position == currentSize)
            addLast(element);
        else
            addMiddle(position, element);
    }

    protected void addMiddle(int position, E element) {
        DoubleListNode<E> prevNode = this.getNode(position - 1);
        DoubleListNode<E> nextNode = prevNode.getNext();
        DoubleListNode<E> newNode = new DoubleListNode<E>(element, prevNode, nextNode);
        prevNode.setNext(newNode);
        nextNode.setPrevious(newNode);
        currentSize++;
    }

    /**
     * Removes the first node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeFirstNode() {
        if (currentSize == 1) {
            this.clear();
        } else {
            head = head.getNext();
            head.setPrevious(null);
            currentSize--;
        }
    }

    @Override
    public E removeFirst() throws EmptyListException {
        if (this.isEmpty())
            throw new EmptyListException();
        DoubleListNode<E> deletedHead = head;
        this.removeFirstNode();
        return deletedHead.getElement();
    }

    /**
     * Removes the last node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeLastNode() {
        if (currentSize == 1) {
            this.clear();
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
            currentSize--;
        }
    }

    @Override
    public E removeLast() throws EmptyListException {
        if (this.isEmpty())
            throw new EmptyListException();
        DoubleListNode<E> deletedTail = tail;
        this.removeLastNode();
        return deletedTail.getElement();
    }

    /**
     * Removes the specified node from the list.
     * Pre-condition: the node is neither the head nor the tail of the list.
     *
     * @param node - middle node to be removed
     */
    protected void removeMiddleNode(DoubleListNode<E> node) {
        DoubleListNode<E> prevNode = node.getPrevious();
        DoubleListNode<E> nextNode = node.getNext();
        prevNode.setNext(nextNode);
        nextNode.setPrevious(prevNode);
        currentSize--;
    }

    @Override
    public E remove(int position) throws InvalidPositionException {
        if (position < 0 || position >= currentSize)
            throw new InvalidPositionException();
        DoubleListNode<E> nodeToRemove = getNode(position);
        if (currentSize == 1)
            this.clear();
        else {
            if (nodeToRemove == head)
                removeFirst();
            else if (nodeToRemove == tail)
                removeLast();
            else {
                removeMiddleNode(nodeToRemove);
            }
        }
        return nodeToRemove.getElement();
    }


    @Override
    public int find(E element) {
        int i = 0;
        for (DoubleListNode<E> node = head; node != null; node = node.getNext()) {
            if (node.getElement().equals(element))
                return i;
            i++;
        }
        return -1;
    }

    /**
     * Returns the node with the first occurrence of the specified element
     * in the list, if the list contains the element.
     * Otherwise, returns null.
     *
     * @param element - element to be searched
     * @return DoubleListNode<E> where element was found, null if not found
     */
    protected DoubleListNode<E> findNode(E element) {
        for (DoubleListNode<E> node = head; node != null; node = node.getNext()) {
            if (node.getElement().equals(element))
                return node;
        }
        return null;
    }


    @Override
    public boolean remove(E element) {
        DoubleListNode<E> node = this.findNode(element);
        if (node == null)
            return false;
        else {
            if (node == head)
                this.removeFirstNode();
            else if (node == tail)
                this.removeLastNode();
            else
                this.removeMiddleNode(node);
            return true;
        }
    }

    //Remove references from head and tail, resulting in
    //the cleaning of the list
    protected void clear() {
        head = null;
        tail = null;
        currentSize = 0;
    }

    /**
     * Removes all the elements from the specified list and
     * inserts them at the end of the list (in proper sequence).
     *
     * @param list - list to be appended to the end of this
     */
    public void append(DoubleList<E> list) {
        if (!list.isEmpty()) {
            DoubleListNode<E> first = list.head;
            DoubleListNode<E> last = list.tail;
            if (isEmpty())
                head = first;
            else {
                first.setPrevious(tail);
                tail.setNext(first);
            }
            tail = last;
            currentSize += list.size();
            list.clear();
        }
    }

}
