package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Get password with @")
    void getNextFriday13th() {
        // given
        String password = "Passw@rd";

        // when
        Boolean hasCharacter = Task4.passwordWithSpecificCharacters(password);

        // then
        assertThat(hasCharacter).isTrue();
    }
}
