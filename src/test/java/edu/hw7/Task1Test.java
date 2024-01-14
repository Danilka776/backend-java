package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Checking that the counter is thread safe")
    void threadSafe() {
        // given
        int numThreads = 10;
        Task1 inc = new Task1();

        // when
        int res = inc.increment(numThreads);

        // then
        assertThat(res).isEqualTo(numThreads);
    }
}
