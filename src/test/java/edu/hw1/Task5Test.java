package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Test1")
    void test1() {
        // given
        Integer num = 11211230;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }
    @Test
    @DisplayName("Test2")
    void test2() {
        // given
        Integer num = 13001120;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }
    @Test
    @DisplayName("Test3")
    void test3() {
        // given
        Integer num = 23336014;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Test4")
    void test4() {
        // given
        Integer num = 11;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Test5")
    void test5() {
        // given
        Integer num = 123312;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Test6")
    void test6() {
        // given
        Integer num = 113312;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isFalse();
    }
}
