package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Default init with digits")
    void test1() {
        // given
        String str = "123456";

        // when
        String res = Task4.fixString(str);

        // then
        assertThat(res).isEqualTo("214365");
    }
    @Test
    @DisplayName("Default init with chars")
    void test2() {
        // given
        String str = "hTsii  s aimex dpus rtni.g";

        // when
        String res = Task4.fixString(str);

        // then
        assertThat(res).isEqualTo("This is a mixed up string.");
    }
    @Test
    @DisplayName("Odd input")
    void test3() {
        // given
        String str = "badce";

        // when
        String res = Task4.fixString(str);

        // then
        assertThat(res).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Empty input")
    void test4() {
        // given
        String str = "";

        // when
        String res = Task4.fixString(str);

        // then
        assertThat(res).isEqualTo("");
    }
}
