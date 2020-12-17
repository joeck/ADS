package ch.zhaw.ads.Praktikum_09;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashtable<K, V> implements Map<K, V> {
    private K[] keys;
    private V[] values;
    private int size;
    private int maxSize;
    // Objekt welches gelöscht wurde
    private final K DELETED = (K) new Object();

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    public MyHashtable(int size) {
        this.maxSize = size;
        clear();
    }

    //  Removes all mappings from this map (optional operation).
    public void clear() {
        keys = (K[]) new Object[maxSize];
        values = (V[]) new Object[maxSize];
        size = 0;
    }

    /**
     * Gibt index
     *
     * @param x key
     * @return -1 falls full oder den hash, bzw key
     */
    public int findPos(Object x) {
        int hash = hash(x);
        int position = 0;

        while (keys[hash] != null && !keys[hash].equals( x ) && position < maxSize) {
            hash = (hash + 1) % keys.length;
            position++;
        }

        if (position == maxSize) return -1;
        return hash;
    }

    /**
     * Vergrößert maxSize mit *2 und fügt alles nochmals hinzu.
     */
    private void rehash() {
        K[] oldKeys = keys;
        V[] oldValues = values;
        maxSize *= 2;
        clear();
        for (int i = 0;i < oldKeys.length; i++) {
            if (oldKeys[i] != null && oldKeys[i] != DELETED) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        int index = findPos(key);
        // wenn index -1, zu klein --> rehash
        if (index < 0 || values[index] == null) {
            if (size > 0.8*maxSize) {
                rehash();
            }

            index = hash(key);
            // index mit linear probing berechnet, zusätzlich ist der key kein deleted key
            while (keys[index] != null && !key.equals(keys[index]) && keys[index] != DELETED) {
                index = (index + 1) % keys.length;
            }
            size++;
            keys[index] = key;
        }
        values[index] = value;
        return value;

    }

    //  Returns the value to which this map maps the specified key.
    public V get(Object key) {
        int h = findPos(key);
        if (key.equals(keys[h])) {
            return values[h];
        }
        else return null;
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        if (size > 0) return false;
        return true;
    }

    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        int h = findPos(key);
        V v = null;
        if (h >= 0 && values[h] != null) {
            v = values[h];
            size--;
            values[h] = null;
            keys[h] = DELETED;
        }
        return v;
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        return size;
    }

    // =======================================================================
    //  Returns a set view of the keys contained in this map.
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    //  Copies all of the mappings from the specified map to this map (optional operation).
    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    //  Returns a collection view of the values contained in this map.
    public Collection values() {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    //  Returns a set view of the mappings contained in this map.
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }

    //  Compares the specified object with this map for equality.
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }

    //  Returns the hash code value for this map.
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}