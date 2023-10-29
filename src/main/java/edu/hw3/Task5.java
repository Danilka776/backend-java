package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Task5 {

    private Task5() {
    }

    public static class Contact {
        private final String name;
        private final String surname;

        Contact(String fullName) {
            name = fullName.split(" ")[0];
            surname = fullName.split(" ").length > 1 ? fullName.split(" ")[1] : "";
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }
        public String toString() {
            return name + " " + surname;
        }
    }

    private static void aesSort(List<Contact> contacts) {
        Contact tmp;
        for (int i = 0; i < contacts.size(); i++) {
            for (int j = 0; j < contacts.size() - 1 - i; j++) {
                String name_j = !Objects.equals(contacts.get(j).getSurname(), "") ?
                    contacts.get(j).getSurname() : contacts.get(j).getName();
                String name_j1 = !Objects.equals(contacts.get(j + 1).getSurname(), "") ?
                    contacts.get(j + 1).getSurname() : contacts.get(j + 1).getName();
                if (name_j.compareTo(name_j1) > 0) {
                    tmp = contacts.get(j);
                    contacts.set(j, contacts.get(j + 1));
                    contacts.set(j + 1, tmp);
                }
            }
        }
    }

    private static void descSort(List<Contact> contacts) {
        Contact tmp;
        for (int i = 0; i < contacts.size(); i++) {
            for (int j = 0; j < contacts.size() - 1 - i; j++) {
                String name_j = !Objects.equals(contacts.get(j).getSurname(), "") ?
                    contacts.get(j).getSurname() : contacts.get(j).getName();
                String name_j1 = !Objects.equals(contacts.get(j + 1).getSurname(), "") ?
                    contacts.get(j + 1).getSurname() : contacts.get(j + 1).getName();
                if (name_j.compareTo(name_j1) < 0) {
                    tmp = contacts.get(j);
                    contacts.set(j, contacts.get(j + 1));
                    contacts.set(j + 1, tmp);
                }
            }
        }
    }


    public static List<Contact> parseContacts(List<String> contacts, String mode) {
        List<Contact> res = new ArrayList<>();
        if (contacts != null && !contacts.isEmpty()) {
            for (String contact : contacts) {
                res.add(new Contact(contact));
            }
            if (mode.equals("AES")) {
                aesSort(res);
            } else {
                descSort(res);
            }
        }
        return res;
    }

}
