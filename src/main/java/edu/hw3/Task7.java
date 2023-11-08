package edu.hw3;

import java.util.Comparator;
import java.util.TreeMap;

public class Task7 {

    private Task7() {
    }


    public static class NewTreeMap<K, V> extends TreeMap<K, V> {
        public NewTreeMap() {
            super(new NewComparator<>());
        }

        public boolean contains(K value) {
            return super.containsKey(value);
        }

        static class NewComparator<K> implements Comparator<K> {
            @SuppressWarnings("unchecked")
            @Override
            public int compare(K key1, K key2) {
                if (key1 == null && key2 == null) {
                    return 0;
                } else if (key1 == null) {
                    return -1;
                } else if (key2 == null) {
                    return 1;
                }
                return ((Comparable<K>) key1).compareTo(key2);
            }
        }


    }

}
