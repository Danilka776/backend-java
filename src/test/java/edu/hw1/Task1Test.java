package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Default init without seconds")
    void test1() {
        // given
        String time = "01:00";

        // when
        int minutes = Task1.minutesToSeconds(time);

        // then
        assertThat(minutes).isEqualTo(60);
    }
    @Test
    @DisplayName("Wrong number of seconds")
    void test2() {
        // given
        String time = "10:60";

        // when
        int minutes = Task1.minutesToSeconds(time);

        // then
        assertThat(minutes).isEqualTo(-1);
    }
    @Test
    @DisplayName("Default init")
    void test3() {
        // given
        String time = "13:56";

        // when
        int minutes = Task1.minutesToSeconds(time);

        // then
        assertThat(minutes).isEqualTo(836);
    }

    @Test
    @DisplayName("Empty init")
    void test4() {
        // given
        String time = "";

        // when
        int minutes = Task1.minutesToSeconds(time);

        // then
        assertThat(minutes).isEqualTo(-1);
    }

    @Test
    @DisplayName("Null init")
    void test5() {
        // given
        String time = null;

        // when
        int minutes = Task1.minutesToSeconds(time);

        // then
        assertThat(minutes).isEqualTo(-1);
    }
}
