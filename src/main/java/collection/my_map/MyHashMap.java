package collection.my_map;

import java.util.Arrays;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private final Entry<K, V>[] table;
    private int size;
    private int threshold;


    private static class Entry<K, V> {

        final K key;
        V value;
        final int hash;
        Entry<K, V> next;

        public Entry(K key, V value, int hash, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.table = (Entry<K, V>[]) new Entry[DEFAULT_CAPACITY];
        this.threshold = (int) (DEFAULT_CAPACITY * LOAD_FACTOR);
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = indexFor(hash, table.length);
        Entry<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hash && current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Entry<K, V> newEntry = new Entry<>(key, value, hash, table[index]);
        table[index] = newEntry;
        size++;
        if (size > threshold) {
            resize();
        }
    }

    @Override
    public boolean remove(K key) {
        int hash = key.hashCode();
        int index = indexFor(hash, table.length);
        Entry<K, V> prev = null;
        Entry<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hash && current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int index = indexFor(hash, table.length);
        Entry<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hash && current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public K[] keyArray() {
        K[] keys = (K[]) new Object[size];
        int indexForKeys = 0;
        for (Entry<K, V> bucketHead : table) {
            Entry<K, V> current = bucketHead;
            while (current != null) {
                keys[indexForKeys++] = current.key;
                current = current.next;
            }
        }
        return keys;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] valueArray() {
        V[] values = (V[]) new Object[size];
        int indexForValues = 0;
        for (Entry<K, V> bucketHead : table) {
            Entry<K, V> current = bucketHead;
            while (current != null) {
                values[indexForValues++] = current.value;
                current = current.next;
            }
        }
        return values;
    }

    private int indexFor(int hash, int capacity) {
        if (hash < 0) {
            hash *= -1;
        }
        return hash % capacity;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldTable = table;
        int newCapacity = oldTable.length * 2;
        Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[newCapacity];
        threshold = (int) (newCapacity * LOAD_FACTOR);

        for (Entry<K, V> bucketHead : oldTable) {
            Entry<K, V> entry = bucketHead;
            while (entry != null) {
                Entry<K, V> next = entry.next;
                int newIndex = indexFor(entry.hash, newCapacity);
                entry.next = newTable[newIndex];
                newTable[newIndex] = entry;
                entry = next;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (Entry<K, V> bucketHead : table) {
            Entry<K, V> current = bucketHead;
            while (current != null) {
                if (index > 0) {
                    builder.append(", ");
                }
                builder.append(current.key).append(" = ").append(current.value);
                index++;
                current = current.next;
            }
        }
        return "MyHashMap{" + builder.append("}");
    }
}