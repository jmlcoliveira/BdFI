package dataStructures;  

/**
 * Separate Chaining Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class SepChainHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public SepChainHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new OrderedDoubleDictionary<K,V>();
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public SepChainHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() ) {
            //TODO: left as an exercise.
            //Original commented, to compile.
            this.rehash();
        }

        //TODO: Left as an exercise.
        Dictionary<K,V> dic = table[hash(key)];
        V oldValue = dic.insert(key, value);
        if(oldValue == null)
            currentSize++;
        return oldValue;
    }

    private void rehash() {
        SepChainHashTable<K,V> newTable = new SepChainHashTable<K,V>(nextPrime(2*currentSize));
        Iterator<Entry<K,V>> it = iterator();
        while(it.hasNext()) {
            Entry<K,V> next = it.next();
            newTable.insert(next.getKey(), next.getValue());
        }
        this.table = newTable.table;
    }

    @Override
    public V remove( K key )
    {
        //TODO: Left as an exercise.
        Dictionary<K,V> dic = table[hash(key)];
        V oldValue = dic.remove(key);
        if(oldValue != null)
            currentSize--;
        return oldValue;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
        //TODO: Left as an exercise.
        List<Entry<K,V>> list = new DoubleList<Entry<K,V>>();
        Iterator<Entry<K,V>> it;
        for(int i = 0; i<table.length; i++) {
            if(!table[i].isEmpty()) {
                it = table[i].iterator();
                while(it.hasNext())
                    list.addLast(it.next());
            }
        }
        return list.iterator();
    } 
}
