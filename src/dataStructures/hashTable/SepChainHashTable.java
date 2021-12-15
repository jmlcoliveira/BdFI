package dataStructures.hashTable;

import dataStructures.Dictionary;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.orderedDictionaries.OrderedDoubleDictionary;

/**
 * Separate Chaining Hash table implementation
 *
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 * @author AED  Team
 * @version 1.0
 */

public class SepChainHashTable<K extends Comparable<K>, V>
        extends HashTable<K, V> {
    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * The array of dictionaries.
     */
    protected Dictionary<K, V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     *
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public SepChainHashTable(int capacity) {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K, V>[]) new Dictionary[arraySize];
        maxSize = capacity;
        currentSize = 0;
    }

    public SepChainHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Returns the hash value of the specified key.
     *
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public V find(K key) {
        int hash = this.hash(key);
        return table[hash] != null ? table[hash].find(key) : null;
    }

    @Override
    public V insert(K key, V value) {
        if (this.isFull())
            this.rehash();

        int hash = this.hash(key);
        Dictionary<K, V> temp = table[hash];
        if (temp != null) {
            V v = temp.insert(key, value);
            if (v == null)
                currentSize++;
            return v;
        } else {
            table[hash] = new OrderedDoubleDictionary<>();
            table[hash].insert(key, value);
            currentSize++;
            return null;
        }
    }

    @Override
    public V remove(K key) {
        int hash = this.hash(key);
        Dictionary<K, V> temp = table[hash];
        if (temp != null) {
            V value = temp.remove(key);
            if (value != null)
                currentSize--;
            return value;
        } else
            return null;
    }

    @Override
    public Iterator<Entry<K, V>> iteratorEntries() {
        return new IteratorEntries();
    }

    @Override
    public Iterator<V> iteratorValues() {
        return new IteratorValues();
    }

    @Override
    public Iterator<K> iteratorKeys() {
        return new IteratorKeys();
    }

    protected void rehash() {
        SepChainHashTable<K, V> temp = new SepChainHashTable<>(table.length * 2);

        Iterator<Entry<K, V>> it = this.iteratorEntries();
        //iterate over all Entries and save them in the new position
        while (it.hasNext()) {
            Entry<K, V> e = it.next();
            temp.insert(e.getKey(), e.getValue());
        }
        this.table = temp.table;
        this.maxSize = temp.maxSize;
    }

    abstract class EntryIterator {

        /**
         * Current index in the dispersion table
         */
        int counter;

        /**
         * Returned elements
         */
        int numberOfReturnedEntry;

        /**
         * Iterator of collision table with elements
         */
        Iterator<Entry<K, V>> it;

        EntryIterator() {
            rewind();
        }

        public boolean hasNext() {
            return it != null && it.hasNext();
        }

        private Entry<K, V> next() {
            return nextNode();
        }

        protected Entry<K, V> nextNode() {
            Entry<K, V> e = it.next();
            numberOfReturnedEntry++;
            findNext();
            return e;
        }

        public void rewind() {
            counter = 0;
            numberOfReturnedEntry = 0;
            findNext();
        }

        /**
         * Finds the next Entry to be returned
         */
        protected void findNext() {
            if (it == null || !it.hasNext() || numberOfReturnedEntry < currentSize) {
                while (counter < table.length && (it == null || !it.hasNext())) {
                    if (table[counter] != null)
                        it = table[counter].iteratorEntries();
                    counter++;
                }
            }
        }
    }

    private class IteratorValues extends EntryIterator implements Iterator<V> {
        @Override
        public V next() {
            return super.next().getValue();
        }
    }

    private class IteratorEntries extends EntryIterator implements Iterator<Entry<K, V>> {
        @Override
        public Entry<K, V> next() {
            return super.next();
        }
    }

    private class IteratorKeys extends EntryIterator implements Iterator<K> {
        @Override
        public K next() {
            return super.next().getKey();
        }
    }
}
