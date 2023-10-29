package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Test
    @DisplayName("String array init 1")
    void test1() {
        // given
        var words = new ArrayList<>(Arrays.asList("a", "bb", "a", "bb"));

        // when
        Map<String, Integer> freq = Task3.freqDict(words);

        // then
        assertEquals(freq, Stream.of(new Object[][] {
            {"bb", 2},
            {"a", 2},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])));
    }

    @Test
    @DisplayName("String array init 2")
    void test2() {
        // given
        var words = new ArrayList<>(Arrays.asList("this", "and", "that", "and"));

        // when
        Map<String, Integer> freq = Task3.freqDict(words);

        // then
        assertEquals(freq, Stream.of(new Object[][] {
            {"that", 1},
            {"and", 2},
            {"this", 1},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])));
    }

    @Test
    @DisplayName("String array init 3")
    void test3() {
        // given
        var words = new ArrayList<>(Arrays.asList("код", "код", "код", "bug"));

        // when
        Map<String, Integer> freq = Task3.freqDict(words);

        // then
        assertEquals(freq, Stream.of(new Object[][] {
            {"код", 3},
            {"bug", 1},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])));
    }

    @Test
    @DisplayName("Integer array init")
    void test4() {
        // given
        var words = new ArrayList<>(Arrays.asList(1, 1, 2, 2));

        // when
        Map<Integer, Integer> freq = Task3.freqDict(words);

        // then
        assertEquals(freq, Stream.of(new Object[][] {
            {1, 2},
            {2, 2},
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (Integer) data[1])));
    }

    @Test
    @DisplayName("Empty input")
    void test5() {
        // given
        var words = new ArrayList<>();

        // when
        Map<?, Integer> freq = Task3.freqDict(words);

        // then
        assertEquals(freq, new HashMap<>());
    }

    @Test
    @DisplayName("One element")
    void test6() {
        // given
        var words = new ArrayList<>(List.of(1.0));

        // when
        Map<Double, Integer> freq = Task3.freqDict(words);

        // then
        assertEquals(freq, Stream.of(new Object[][] {
            {1.0, 1},
        }).collect(Collectors.toMap(data -> (Double) data[0], data -> (Integer) data[1])));
    }

    @Test
    @DisplayName("Identical elements")
    void test7() {
        // given
        var words = new ArrayList<>(List.of("a", "a", "a", "a"));

        // when
        Map<String, Integer> freq = Task3.freqDict(words);

        // then
        assertEquals(freq, Stream.of(new Object[][] {
            {"a", 4},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1])));
    }
}
