package collection.my_map;

import java.util.Arrays;

public class MyHashMap implements MyMap {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private final Entry[] table;
    private int size;
    private int threshold;


    private static class Entry {

        final String key;
        Integer value;
        final int hash;
        Entry next;

        public Entry(String key, Integer value, int hash, Entry next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key='" + key + '\'' +
                    ", value=" + value +
                    '}';
        }

    }

    public MyHashMap() {
        this.table = new Entry[DEFAULT_CAPACITY];
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
    public void put(String key, Integer value) {
        int hash = key.hashCode();
        int index = indexFor(hash, table.length);
        Entry current = table[index];
        while (current != null) {
            if (current.hash == hash && current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Entry newEntry = new Entry(key, value, hash, table[index]);
        table[index] = newEntry;
        size++;
        if (size > threshold) {
            resize();
        }
    }

    @Override
    public boolean remove(String key) {
        int hash = key.hashCode();
        int index = indexFor(hash, table.length);
        Entry prev = null;
        Entry current = table[index];
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
    public Integer get(String key) {
        int hash = key.hashCode();
        int index = indexFor(hash, table.length);
        Entry current = table[index];
        while (current != null) {
            if (current.hash == hash && current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return 0;
    }

    @Override
    public String[] keyArray() {
        String[] keys = new String[size];
        int indexForKeys = 0;
        for (Entry bucketHead : table) {
            Entry current = bucketHead;
            while (current != null) {
                keys[indexForKeys++] = current.key;
                current = current.next;
            }
        }
        return keys;
    }

    @Override
    public Integer[] valueArray() {
        Integer[] values = new Integer[size];
        int indexForValues = 0;
        for (Entry bucketHead : table) {
            Entry current = bucketHead;
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

    private void resize() {
        Entry[] oldTable = table;
        int newCapacity = oldTable.length * 2;
        Entry[] newTable = new Entry[newCapacity];
        threshold = (int) (newCapacity * LOAD_FACTOR);

        for (Entry bucketHead : oldTable) {
            Entry entry = bucketHead;
            while (entry != null) {
                Entry next = entry.next;
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
        for (Entry bucketHead : table) {
            Entry current = bucketHead;
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