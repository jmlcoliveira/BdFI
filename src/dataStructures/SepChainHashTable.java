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
            table[i] = new OrderedDoubleDictionary<K, V>();
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
        if (this.isFull()) {
            this.rehash();
        }

        Dictionary<K, V> dic = table[hash(key)];
        V oldValue = dic.insert(key, value);
        if (oldValue == null)
            currentSize++;
        return oldValue;
    }

    private void rehash() {
        SepChainHashTable<K, V> newTable = new SepChainHashTable<K, V>(2 * currentSize);
        Iterator<Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            Entry<K, V> next = it.next();
            newTable.insert(next.getKey(), next.getValue());
        }
        this.table = newTable.table;
        this.maxSize = newTable.maxSize;
    }

    @Override
    public V remove(K key) {
        //TODO: Left as an exercise.
        Dictionary<K, V> dic = table[hash(key)];
        V oldValue = dic.remove(key);
        if (oldValue != null)
            currentSize--;
        return oldValue;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new EntryIterator();
    }

    class EntryIterator implements Iterator<Entry<K, V>>, Serializable {

        static final long serialVersionUID = 0L;

        int counter;

        int numberOfReturnedEntry;

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

        private Entry<K, V> nextNode() {
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

        protected void findNext() {
            if (it == null || !it.hasNext() || numberOfReturnedEntry < currentSize) {
                while (it == null || (counter < table.length && !it.hasNext()))
                    it = table[counter++].iterator();
            }
        }
    }
}
































