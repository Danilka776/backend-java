package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Task3Test {
    @Test
    @DisplayName("Checks first type of data")
    void firstType() {
        // given
        String date = "2020-10-10";

        // when
        Optional<LocalDate> isDate = Task3.parseDate(date);

        // then
        assertThat(isDate.toString()).isEqualTo("Optional[2020-10-10]");
    }

    @Test
    @DisplayName("Checks second type of data")
    void secondType() {
        // given
        String date = "2020-12-2";

        // when
        Optional<LocalDate> isDate = Task3.parseDate(date);

        // then
        assertThat(isDate.toString()).isEqualTo("Optional[2020-12-02]");
    }

    @Test
    @DisplayName("Checks third type of data")
    void thirdType() {
        // given
        String date = "1/3/1976";

        // when
        Optional<LocalDate> isDate = Task3.parseDate(date);

        // then
        assertThat(isDate.toString()).isEqualTo("Optional[1976-03-01]");
    }

    @Test
    @DisplayName("Checks fourth type of data")
    void fourthType() {
        // given
        String date = "1/3/20";

        // when
        Optional<LocalDate> isDate = Task3.parseDate(date);

        // then
        assertThat(isDate.toString()).isEqualTo("Optional[2020-03-01]");
    }

    @Test
    @DisplayName("Checks that tomorrow is correct data")
    void tomorrow() {
        // given
        String date = "tomorrow";

        // when
        Optional<LocalDate> isDate = Task3.parseDate(date);

        // then
        assertThat(isDate.toString()).isEqualTo("Optional[2023-11-14]");
    }

    @Test
    @DisplayName("Checks that today is correct data")
    void today() {
        // given
        String date = "today";

        // when
        Optional<LocalDate> isDate = Task3.parseDate(date);

        // then
        assertThat(isDate.toString()).isEqualTo("Optional[2023-11-13]");
    }

    @Test
    @DisplayName("Checks that yesterday is correct data")
    void yesterday() {
        // given
        String date = "yesterday";

        // when
        Optional<LocalDate> isDate = Task3.parseDate(date);

        // then
        assertThat(isDate.toString()).isEqualTo("Optional[2023-11-12]");
    }

    @Test
    @DisplayName("Checks that 1 day ago is correct data")
    void oneDayAgo() {
        // given
        String date = "1 day ago";

        // when
        Optional<LocalDate> isDate = Task3.parseDate(date);

        // then
        assertThat(isDate.toString()).isEqualTo("Optional[2023-11-12]");
    }

    @Test
    @DisplayName("Checks that 20 days ago is correct data")
    void twentyDayAgo() {
        // given
        String date = "20 days ago";

        // when
        Optional<LocalDate> isDate = Task3.parseDate(date);

        // then
        assertThat(isDate.toString()).isEqualTo("Optional[2023-10-24]");
    }

    @Test
    @DisplayName("Checks not correct data")
    void notCorrectData() {
        // given
        String date = "no data";

        // when
        Optional<LocalDate> isDate = Task3.parseDate(date);

        // then
        assertThat(isDate).isEmpty();
    }
}
