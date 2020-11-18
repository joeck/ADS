import java.util.*;

public class MyHashtable<K,V> implements java.util.Map<K,V> {
    private K[] keys =   (K[]) new Object[10];
    private V[] values = (V[]) new Object[10];


    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % values.length;
    }

    public MyHashtable(int size) {
        // to be done todo
        keys =   (K[]) new Object[size];
        values = (V[]) new Object[size];
    }

    //  Removes all mappings from this map (optional operation).
    public void clear() {
        // to be done todo
        keys =   (K[]) new Object[keys.length];
        values = (V[]) new Object[keys.length];
        //throw new UnsupportedOperationException();
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        // to be done todo
        if(size() == keys.length) {
            throw new UnsupportedOperationException();
        }

        int currentPos = hash(key);

        while (keys[currentPos] != null && !keys[currentPos].equals(key)) {
            currentPos = (currentPos + 1) % values.length;
        }
        values[currentPos] = value;
        keys[currentPos] = key;

        return values[currentPos];
        //throw new UnsupportedOperationException();
    }


    //  Returns the value to which this map maps the specified key.
    public V get(Object key) {
        // to be done todo
        int currentPos = hash(key);

        while (values[currentPos] != null && !values[currentPos].equals(key)) {
            currentPos = (currentPos + 1) % values.length;
        }
        return values[currentPos];
        //throw new UnsupportedOperationException();
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        // to be done todo
        return size() != 0;
        //throw new UnsupportedOperationException();
    }

    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        // to be done (Aufgabe 3) todo
        int currentPos = hash(key);
        values[currentPos] = null;
        keys[currentPos] = null;
        restruct();
        return values[currentPos];
        //throw new UnsupportedOperationException();
    }

    private void restruct() {
        K[] cloneKeys = keys.clone();
        V[] cloneValues = values.clone();
        clear();

        for (int i = 0; i < keys.length; i++){
            if (cloneKeys[i] != null) {
                put(cloneKeys[i], cloneValues[i]);
            }
        }
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        // to be done todo
        int count = 0;
        for (int i = 0; i < keys.length; i++){
            if (keys[i] != null) {
                count++;
            }
        }
        return count;
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
    public boolean containsValue(Object value)  {
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