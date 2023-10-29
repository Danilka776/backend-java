package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Descendant in 3 palindrome")
    void test1() {
        // given
        Integer num = 11211230;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }
    @Test
    @DisplayName("Descendant in 2 palindrome")
    void test2() {
        // given
        Integer num = 13001120;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }
    @Test
    @DisplayName("Descendant palindrome")
    void test3() {
        // given
        Integer num = 23336014;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Simply palindrome")
    void test4() {
        // given
        Integer num = 11;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Descendant palindrome")
    void test5() {
        // given
        Integer num = 123312;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Not palindrome")
    void test6() {
        // given
        Integer num = 113312;

        // when
        boolean res = Task5.isPalindromeDescendant(num);

        // then
        assertThat(res).isFalse();
    }
}
