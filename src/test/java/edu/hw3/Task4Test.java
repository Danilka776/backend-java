package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task4Test {
    @Test
    @DisplayName("ConvertToRomanSimpleNumber")
    void test1() {
        // given
        Integer num = 2;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("II");
    }

    @Test
    @DisplayName("ConvertToRomanNumberWithTens")
    void test2() {
        // given
        Integer num = 12;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("XII");
    }

    @Test
    @DisplayName("ConvertToRomanNumberWithHundreds")
    void test3() {
        // given
        Integer num = 116;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("CXVI");
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
        Integer num = null;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertNull(roman);
    }

    @Test
    @DisplayName("TryToConvertExceedingNumber")
    void test7() {
        // given
        Integer num = 32000000;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertNull(roman);
    }

}
