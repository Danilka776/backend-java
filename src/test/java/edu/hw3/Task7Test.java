package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("ContainsNullAsKey")
    void test1() {
        // given
        TreeMap<String, String> tree = new Task7.NewTreeMap<>();

        // when
        tree.put(null, "test");
        // then
        assertThat(((Task7.NewTreeMap<String, String>) tree).contains(null)).isTrue();
    }

}
