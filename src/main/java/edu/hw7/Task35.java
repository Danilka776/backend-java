package edu.hw7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task35 {

    private Task35() {
    }

    public record Person(int id, String name, String address, String phoneNumber) {}

    interface PersonDatabase {
        void add(Person person);

        void delete(int id);

        List<Person> findByName(String name);

        List<Person> findByAddress(String address);

        List<Person> findByPhone(String phone);
    }

    static class MyPersonDatabase implements PersonDatabase {


        Map<Integer, Person> databaseById = new HashMap<>();
        Map<String, Set<Integer>> dataByName = new HashMap<>();
        Map<String, Set<Integer>> dataByNumber = new HashMap<>();
        Map<String, Set<Integer>> dataByAddress = new HashMap<>();
        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final Lock readLock = lock.readLock();
        private final Lock writeLock = lock.writeLock();

        @Override
        public void add(Person person) {
            writeLock.lock();
            try {
                databaseById.put(person.id(), person);

                addToIndex(dataByName, person.name(), person.id());
                addToIndex(dataByAddress, person.address(), person.id());
                addToIndex(dataByNumber, person.phoneNumber(), person.id());
            } finally {
                writeLock.unlock();
            }

        }

        @Override
        public void delete(int id) {
            writeLock.lock();
            try {
                Person person = databaseById.remove(id);
                if (person != null) {
                    removeByKey(dataByName, person.name(), id);
                    removeByKey(dataByAddress, person.address(), id);
                    removeByKey(dataByNumber, person.phoneNumber(), id);
                }
            } finally {
                writeLock.unlock();
            }
        }

        @Override
        public List<Person> findByName(String name) {
            readLock.lock();
            try {
                return findByKey(dataByName, name);
            } finally {
                readLock.unlock();
            }
        }

        @Override
        public List<Person> findByAddress(String address) {
            readLock.lock();
            try {
                return findByKey(dataByAddress, address);
            } finally {
                readLock.unlock();
            }
        }

        @Override
        public List<Person> findByPhone(String phone) {
            readLock.lock();
            try {
                return findByKey(dataByNumber, phone);
            } finally {
                readLock.unlock();
            }
        }

        private void addToIndex(Map<String, Set<Integer>> database, String key, int id) {
            database.computeIfAbsent(key, k -> ConcurrentHashMap.newKeySet()).add(id);
        }

        private void removeByKey(Map<String, Set<Integer>> database, String key, int id) {
            database.computeIfPresent(key, (k, val) -> {
                val.remove(id);
                if (val.isEmpty()) {
                    return null;
                }
                return val;
            });
        }

        private List<Person> findByKey(Map<String, Set<Integer>> database, String key) {
            Set<Integer> indexes = database.getOrDefault(key, Collections.emptySet());
            List<Person> result = new ArrayList<>();
            for (int id : indexes) {
                Person person = databaseById.get(id);
                if (person != null) {
                    result.add(person);
                }
            }
            return result;
        }
    }

}
