package dataStructures;

import java.io.Serializable;

public class EntryClass<K extends Comparable<K>, V> implements Serializable, Entry<K,V> {
    static final long serialVersionUID = 0L;

    private K key;
    private V value;

    public EntryClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
