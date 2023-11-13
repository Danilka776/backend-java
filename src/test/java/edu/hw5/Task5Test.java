package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Checks the palate with Russian character")
    void plateWithRusChar() {
        // given
        String plate = "А123ВГ77";

        // when
        Boolean hasCharacter = Task5.validCarPlate(plate);

        // then
        assertThat(hasCharacter).isFalse();
    }

    @Test
    @DisplayName("Checks the good plate")
    void correctPlate1() {
        // given
        String plate = "О777ОО177";

        // when
        Boolean hasCharacter = Task5.validCarPlate(plate);

        // then
        assertThat(hasCharacter).isTrue();
    }

    @Test
    @DisplayName("Checks the good plate")
    void correctPlate2() {
        // given
        String plate = "А123ВЕ777";

        // when
        Boolean hasCharacter = Task5.validCarPlate(plate);

        // then
        assertThat(hasCharacter).isTrue();
    }

    @Test
    @DisplayName("Checks the palate without first character")
    void test4() {
        // given
        String plate = "123АВЕ777";

        // when
        Boolean hasCharacter = Task5.validCarPlate(plate);

        // then
        assertThat(hasCharacter).isFalse();
    }
}
