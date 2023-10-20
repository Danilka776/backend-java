package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Default init a in b")
    void test1() {
        // given
        int[] a = new int[] {1, 2, 3, 4};
        int[] b = new int[] {0, 6};

        // when
        boolean res = Task3.isNestable(a, b);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Default init a in b")
    void test2() {
        // given
        int[] a = new int[] {3, 1};
        int[] b = new int[] {4, 0};

        // when
        boolean res = Task3.isNestable(a, b);

        // then
        assertThat(res).isTrue();
    }

    @Test
    @DisplayName("Default init a not in b")
    void test3() {
        // given
        int[] a = new int[] {9, 9, 8};
        int[] b = new int[] {8, 9};

        // when
        boolean res = Task3.isNestable(a, b);

        // then
        assertThat(res).isFalse();
    }

    @Test
    @DisplayName("A is wider than b")
    void test4() {
        // given
        int[] a = new int[] {1, 2, 3, 4};
        int[] b = new int[] {2, 3};

        // when
        boolean res = Task3.isNestable(a, b);

        // then
        assertThat(res).isFalse();
    }
}
