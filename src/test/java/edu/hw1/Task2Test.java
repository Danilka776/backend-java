package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Test1")
    void test1() {
        // given
        int num = 4666;

        // when
        int res = Task2.countDigits(num);

        // then
        assertThat(res).isEqualTo(4);
    }
    @Test
    @DisplayName("Test2")
    void test2() {
        // given
        int num = 544;

        // when
        int res = Task2.countDigits(num);

        // then
        assertThat(res).isEqualTo(3);
    }
    @Test
    @DisplayName("Test3")
    void test3() {
        // given
        int num = 0;

        // when
        int res = Task2.countDigits(num);

        // then
        assertThat(res).isEqualTo(1);
    }
    @Test
    @DisplayName("Test4")
    void test4() {
        // given
        int num = -15;

        // when
        int res = Task2.countDigits(num);

        // then
        assertThat(res).isEqualTo(2);
    }
}
