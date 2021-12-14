package dataStructures.orderedDictionaries;

import dataStructures.Entry;

import java.io.Serializable;

class EntryClass<K extends Comparable<K>, V> implements Serializable, Entry<K, V>, Comparable<EntryClass<K, V>> {
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

    @Override
    public int compareTo(EntryClass<K, V> o) {
        return key.compareTo(o.getKey());
    }
}
