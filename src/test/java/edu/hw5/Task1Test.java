package edu.hw5;

import edu.hw1.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Normal data parse")
    void test1() {
        // given
        String date1 = "2022-03-12, 20:20 - 2022-03-12, 23:50";
        String date2 = "2022-04-01, 21:30 - 2022-04-02, 01:20";
        String[] date = new String[]{date1, date2};

        // when
        String duration = edu.hw5.Task1.period(date);

        // then
        assertThat(duration).isEqualTo("3ч 40м");
    }

}
