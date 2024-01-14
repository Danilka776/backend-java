package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task2Test {
    @Test
    @DisplayName("Outputs all dates from Friday the 13th in 1925")
    void getFriday13th1925() {
        // given
        int year = 1925;

        // when
        String[] fridays = edu.hw5.Task2.getFridays13th(year);

        // then
        assertArrayEquals(fridays, new String[]{"1925-02-13", "1925-03-13", "1925-11-13"});
    }

    @Test
    @DisplayName("Outputs all dates from Friday the 13th in 2024")
    void getFriday13th2024() {
        // given
        int year = 2024;

        // when
        String[] fridays = edu.hw5.Task2.getFridays13th(year);

        // then
        assertArrayEquals(fridays, new String[]{"2024-09-13", "2024-12-13"});
    }

    @Test
    @DisplayName("Outputs next Friday the 13th after the given date")
    void getNextFriday13th() {
        // given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2024-09-05", formatter);

        // when
        LocalDate friday = edu.hw5.Task2.getNextFridayThe13th(date);

        // then
        assertThat(friday.toString()).isEqualTo("2024-09-13");
    }
}
