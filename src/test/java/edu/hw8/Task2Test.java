package edu.hw8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Checking the correctness of the calculation of the 10th Fibonacci number")
    void correct10thNumber() {
        // given
        int numOfSequence = 45;
        int numOfThread = 3;

        // when
        Integer[] res = Task2.calculateFibonacciSequence(numOfThread, numOfSequence);
        System.out.println(Arrays.stream(res).toList());

        // then
        assertThat(res[10]).isEqualTo(55);
    }

}
