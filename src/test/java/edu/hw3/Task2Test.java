package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task2Test {
    @Test
    @DisplayName("Simple input")
    void test1() {
        // given
        String text = "()()()";

        // when
        String[] clusters = Task2.clusterize(text).toArray(new String[0]);;

        // then
        assertArrayEquals(clusters, new String[]{"()", "()", "()"});
    }

    @Test
    @DisplayName("No change")
    void test2() {
        // given
        String text = "((()))";

        // when
        String[] clusters = Task2.clusterize(text).toArray(new String[0]);;

        // then
        assertArrayEquals(clusters, new String[]{"((()))"});
    }

    @Test
    @DisplayName("Difficult input 1")
    void test3() {
        // given
        String text = "((()))(())()()(()())";

        // when
        String[] clusters = Task2.clusterize(text).toArray(new String[0]);;

        // then
        assertArrayEquals(clusters, new String[]{"((()))", "(())", "()", "()", "(()())"});
    }

    @Test
    @DisplayName("Difficult input 2")
    void test4() {
        // given
        String text = "((())())(()(()()))";

        // when
        String[] clusters = Task2.clusterize(text).toArray(new String[0]);;

        // then
        assertArrayEquals(clusters, new String[]{"((())())", "(()(()()))"});
    }

    @Test
    @DisplayName("Empty input")
    void test5() {
        // given
        String text = "";

        // when
        String[] clusters = Task2.clusterize(text).toArray(new String[0]);;

        // then
        assertArrayEquals(clusters, new String[]{""});
    }

    @Test
    @DisplayName("Null input")
    void test6() {
        // given
        String text = null;

        // when
        String[] clusters = Task2.clusterize(text).toArray(new String[0]);;

        // then
        assertArrayEquals(clusters, new String[]{});
    }

    @Test
    @DisplayName("Invalid input")
    void test7() {
        // given
        String text = ")(())(";

        // when
        String[] clusters = Task2.clusterize(text).toArray(new String[0]);;

        // then
        assertArrayEquals(clusters, new String[]{});
    }

    @Test
    @DisplayName("Wrong input")
    void test8() {
        // given
        String text = "())()))))";

        // when
        String[] clusters = Task2.clusterize(text).toArray(new String[0]);;

        // then
        assertArrayEquals(clusters, new String[]{});
    }

    @Test
    @DisplayName("Not bracket")
    void test9() {
        // given
        String text = "())()k)))";

        // when
        String[] clusters = Task2.clusterize(text).toArray(new String[0]);;

        // then
        assertArrayEquals(clusters, new String[]{});
    }
}
