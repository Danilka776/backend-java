package edu.hw7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Task3 {

    private Task3() {
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
        Map<String, Set<Integer>> dataByName = new ConcurrentHashMap<>();
        Map<String, Set<Integer>> dataByNumber = new ConcurrentHashMap<>();
        Map<String, Set<Integer>> dataByAddress = new ConcurrentHashMap<>();

        @Override
        public synchronized void add(Person person) {
            databaseById.put(person.id(), person);

            addToIndex(dataByName, person.name(), person.id());
            addToIndex(dataByAddress, person.address(), person.id());
            addToIndex(dataByNumber, person.phoneNumber(), person.id());

        }

        @Override
        public synchronized void delete(int id) {
            Person person = databaseById.remove(id);
            if (person != null) {
                removeByKey(dataByName, person.name(), id);
                removeByKey(dataByAddress, person.address(), id);
                removeByKey(dataByNumber, person.phoneNumber(), id);
            }
        }

        @Override
        public synchronized List<Person> findByName(String name) {
            return findByKey(dataByName, name);
        }

        @Override
        public synchronized List<Person> findByAddress(String address) {
            return findByKey(dataByAddress, address);
        }

        @Override
        public synchronized List<Person> findByPhone(String phone) {
            return findByKey(dataByNumber, phone);
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
