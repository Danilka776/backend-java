package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Test1")
    void test1() {
        // given
        String time = "01:00";

        // when
        int minutes = Task1.minutesToSeconds(time);

        // then
        assertThat(minutes).isEqualTo(60);
    }
    @Test
    @DisplayName("Test2")
    void test2() {
        // given
        String time = "10:60";

        // when
        int minutes = Task1.minutesToSeconds(time);

        // then
        assertThat(minutes).isEqualTo(-1);
    }
    @Test
    @DisplayName("Test3")
    void test3() {
        // given
        String time = "13:56";

        // when
        int minutes = Task1.minutesToSeconds(time);

        // then
        assertThat(minutes).isEqualTo(836);
    }
}
