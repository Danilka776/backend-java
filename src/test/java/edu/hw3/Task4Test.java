package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task4Test {
    @Test
    @DisplayName("Convert to roman number 2")
    void convertToRomanSimpleNumber() {
        // given
        Integer num = 2;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("II");
    }

    @Test
    @DisplayName("Convert to roman number with tens")
    void convertToRomanNumberWithTens() {
        // given
        Integer num = 12;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("XII");
    }

    @Test
    @DisplayName("Convert to roman number with hundreds")
    void convertToRomanNumberWithHundreds() {
        // given
        Integer num = 116;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("CXVI");
    }

    @Test
    @DisplayName("Get on input num 3999")
    void maxInput() {
        // given
        Integer num = 3999;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("MMMCMXCIX");
    }

    @Test
    @DisplayName("Get on input num 0")
    void zeroInput() {
        // given
        Integer num = 0;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertThat(roman).isEqualTo("");
    }

    @Test
    @DisplayName("Get on input null")
    void invalidInput() {
        // given
        Integer num = null;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertNull(roman);
    }

    @Test
    @DisplayName("Get on input number 32'000'000")
    void TryToConvertExceedingNumber() {
        // given
        Integer num = 32000000;

        // when
        String roman = Task4.convertToRoman(num);

        // then
        assertNull(roman);
    }

}
