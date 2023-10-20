package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Simple right rotate")
    void test1() {
        // given
        int n = 8;
        int shift = 1;

        // when
        int res = Task7.rotateRight(n, shift);

        // then
        assertThat(res).isEqualTo(4);
    }

    @Test
    @DisplayName("Left rotate 1")
    void test2() {
        // given
        int n = 16;
        int shift = 1;

        // when
        int res = Task7.rotateLeft(n, shift);

        // then
        assertThat(res).isEqualTo(1);
    }

    @Test
    @DisplayName("Left rotate 2")
    void test3() {
        // given
        int n = 17;
        int shift = 2;

        // when
        int res = Task7.rotateLeft(n, shift);

        // then
        assertThat(res).isEqualTo(6);
    }

    @Test
    @DisplayName("No changes right")
    void test4() {
        // given
        int n = 35;
        int shift = 0;

        // when
        int res = Task7.rotateRight(n, shift);

        // then
        assertThat(res).isEqualTo(35);
    }

    @Test
    @DisplayName("No changes left")
    void test5() {
        // given
        int n = 35;
        int shift = 0;

        // when
        int res = Task7.rotateLeft(n, shift);

        // then
        assertThat(res).isEqualTo(35);
    }

    @Test
    @DisplayName("Zero shift right")
    void test6() {
        // given
        int n = 0;
        int shift = 20;

        // when
        int res = Task7.rotateRight(n, shift);

        // then
        assertThat(res).isEqualTo(0);
    }

    @Test
    @DisplayName("Zero shift left")
    void test7() {
        // given
        int n = 0;
        int shift = 20;

        // when
        int res = Task7.rotateLeft(n, shift);

        // then
        assertThat(res).isEqualTo(0);
    }
}
