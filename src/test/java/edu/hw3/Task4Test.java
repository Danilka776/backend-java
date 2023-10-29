package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task4Test {
    @Test
    @DisplayName("Simple input 1")
    void test1() {
        // given
        Integer num = 2;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("II");
    }

    @Test
    @DisplayName("Simple input 2")
    void test2() {
        // given
        Integer num = 12;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("XII");
    }

    @Test
    @DisplayName("Simple input 3")
    void test3() {
        // given
        Integer num = 16;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("XVI");
    }

    @Test
    @DisplayName("Max input")
    void test4() {
        // given
        Integer num = 3999;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("MMMCMXCIX");
    }

    @Test
    @DisplayName("Zero input")
    void test5() {
        // given
        Integer num = 0;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("");
    }

    @Test
    @DisplayName("Invalid input")
    void test6() {
        // given
        Integer num = 4553;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertNull(roman);
    }

}
