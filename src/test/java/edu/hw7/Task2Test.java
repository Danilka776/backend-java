package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Checking for correct computation of factorial")
    void correctFactorial() {
        // given
        int num = 6;

        // when
        int res = Task2.factorial(num);

        // then
        assertThat(res).isEqualTo(720);
    }
    @Test
    @DisplayName("Checking for correct computation of factorial of zero ")
    void correctZeroFactorial() {
        // given
        int num = 0;

        // when
        int res = Task2.factorial(num);

        // then
        assertThat(res).isEqualTo(1);
    }
}
