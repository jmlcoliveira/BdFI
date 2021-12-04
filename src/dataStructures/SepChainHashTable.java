package dataStructures;

import java.io.Serializable;

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
        for (int i = 0; i < arraySize; i++)
            table[i] = new OrderedDoubleDictionary<>();
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
        return table[this.hash(key)].find(key);
    }

    @Override
    public V insert(K key, V value) {
        if (this.isFull())
            this.rehash();

        V v = table[this.hash(key)].insert(key, value);
        if (v == null)
            currentSize++;
        return v;
    }

    @Override
    public V remove(K key) {
        V value = table[this.hash(key)].remove(key);
        if (value != null)
            currentSize--;
        return value;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new EntryIterator();
    }

    protected void rehash() {
        SepChainHashTable<K, V> temp = new SepChainHashTable<>(table.length * 2);

        Iterator<Entry<K, V>> it = this.iterator();
        //iterate over all Entries and save them in the new position
        while (it.hasNext()) {
            Entry<K, V> e = it.next();
            temp.insert(e.getKey(), e.getValue());
        }
        this.table = temp.table;
        this.maxSize = temp.maxSize;
    }

    class EntryIterator implements Iterator<Entry<K, V>>, Serializable {

        static final long serialVersionUID = 0L;

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
            return it.hasNext();
        }

        public final Entry<K, V> next() {
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
                while (it == null || (counter < table.length && !it.hasNext()))
                    it = table[counter++].iterator();
            }
        }
    }
}
