package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int i = indexFor(hash(newHashcode(key)));
        if (table[i] == null) {
            table[i] =  new MapEntry<>(key, value);
            rsl = true;
            modCount++;
            count++;
        }
        if (count == (int) (capacity * LOAD_FACTOR)) {
            expand();
        }
        return rsl;
    }

    private int newHashcode(K key) {
        return key != null ? key.hashCode() : 0;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null) {
                newTable[indexFor(hash(newHashcode(kvMapEntry.key)))] =
                        new MapEntry<>(kvMapEntry.key, kvMapEntry.value);
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int i = indexFor(hash(newHashcode(key)));
        if (table[i] != null && hash(newHashcode(table[i].key)) == hash(newHashcode(key))
                && Objects.equals(key, table[i].key)) {
                rsl = table[i].value;
        }
        return rsl;
     }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int i = indexFor(hash(newHashcode(key)));
        if (table[i] != null) {
            table[i] = null;
            modCount++;
            count--;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int counter;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (counter < capacity && table[counter] == null) {
                    counter++;
                }
                return counter < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[counter++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}