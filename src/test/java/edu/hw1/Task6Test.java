package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Test1")
    void test1() {
        // given
        int num = 3524;

        // when
        int res = Task6.k(num);

        // then
        assertThat(res).isEqualTo(3);
    }

    @Test
    @DisplayName("Test2")
    void test2() {
        // given
        int num = 6621;

        // when
        int res = Task6.k(num);

        // then
        assertThat(res).isEqualTo(5);
    }

    @Test
    @DisplayName("Test3")
    void test3() {
        // given
        int num = 6554;

        // when
        int res = Task6.k(num);

        // then
        assertThat(res).isEqualTo(4);
    }

    @Test
    @DisplayName("Test4")
    void test4() {
        // given
        int num = 1234;

        // when
        int res = Task6.k(num);

        // then
        assertThat(res).isEqualTo(3);
    }
}
