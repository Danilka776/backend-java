package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task5Test {
    @Test
    @DisplayName("Default input 1")
    void test1() {
        // given
        List<String> contacts = new ArrayList<>(List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"));
        String mode = "AES";

        // when
        List<Task5.Contact> sortedContact = Task5.parseContacts(contacts, mode);
        String[] res = new String[sortedContact.size()];
        for (int i = 0; i < sortedContact.size(); i++) {
            res[i] = sortedContact.get(i).toString();
        }

        // then
        assertArrayEquals(res, new String[]{"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"});
    }

    @Test
    @DisplayName("Default input 2")
    void test2() {
        // given
        List<String> contacts = new ArrayList<>(List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss"));
        String mode = "DESC";

        // when
        List<Task5.Contact> sortedContact = Task5.parseContacts(contacts, mode);
        String[] res = new String[sortedContact.size()];
        for (int i = 0; i < sortedContact.size(); i++) {
            res[i] = sortedContact.get(i).toString();
        }

        // then
        assertArrayEquals(res, new String[]{"Carl Gauss", "Leonhard Euler", "Paul Erdos"});
    }

    @Test
    @DisplayName("Input without surname")
    void test3() {
        // given
        List<String> contacts = new ArrayList<>(List.of("Paul Ardos", "Ben", "Carl Causs"));
        String mode = "AES";

        // when
        List<Task5.Contact> sortedContact = Task5.parseContacts(contacts, mode);
        String[] res = new String[sortedContact.size()];
        for (int i = 0; i < sortedContact.size(); i++) {
            res[i] = sortedContact.get(i).toString();
        }

        // then
        assertArrayEquals(res, new String[]{"Paul Ardos", "Ben ", "Carl Causs"});
    }

    @Test
    @DisplayName("Empty input")
    void test4() {
        // given
        List<String> contacts = new ArrayList<>();
        String mode = "AES";

        // when
        List<Task5.Contact> sortedContact = Task5.parseContacts(contacts, mode);
        String[] res = new String[sortedContact.size()];
        for (int i = 0; i < sortedContact.size(); i++) {
            res[i] = sortedContact.get(i).toString();
        }

        // then
        assertArrayEquals(res, new String[]{});
    }

    @Test
    @DisplayName("Empty input")
    void test5() {
        // given
        List<String> contacts = null;
        String mode = "AES";

        // when
        List<Task5.Contact> sortedContact = Task5.parseContacts(contacts, mode);
        String[] res = new String[sortedContact.size()];
        for (int i = 0; i < sortedContact.size(); i++) {
            res[i] = sortedContact.get(i).toString();
        }

        // then
        assertArrayEquals(res, new String[]{});
    }

}
