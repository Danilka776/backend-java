package edu.hw10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.hw10.Task2.CacheProxy.FibCalculator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Checking the correctness of the calculation of the 8th Fibonacci number")
    void correctCalculation() {
        // given
        int num = 8;
        FibCalculator fibCalculator = new edu.hw10.Task2.CacheProxy.FibCalculatorImpl();
        FibCalculator proxy = edu.hw10.Task2.CacheProxy.create(fibCalculator, fibCalculator.getClass());

        // when
        long result = proxy.fib(num);

        // then
        assertThat(result).isEqualTo(21);
    }

}
