package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Checks that the substring is inside the string")
    void substrInStr() {
        // given
        String s = "abc";
        String t = "achfdbaabgabcaabg";

        // when
        Boolean hasCharacter = Task6.subsequence(s, t);

        // then
        assertThat(hasCharacter).isTrue();
    }

    @Test
    @DisplayName("Checks that the substring is not inside the string")
    void substrNotInStr() {
        // given
        String s = "abc";
        String t = "achfdbaabgabdcaabg";

        // when
        Boolean hasCharacter = Task6.subsequence(s, t);

        // then
        assertThat(hasCharacter).isFalse();
    }

    @Test
    @DisplayName("Checks that the substring is at the beginning of the string")
    void substrAtBeginStr() {
        // given
        String s = "abc";
        String t = "abchfdbaabgabdcaabg";

        // when
        Boolean hasCharacter = Task6.subsequence(s, t);

        // then
        assertThat(hasCharacter).isTrue();
    }

    @Test
    @DisplayName("Checks that the substring is at the end of the string")
    void substrAtEndStr() {
        // given
        String s = "abc";
        String t = "achfdbaabgabdcaabc";

        // when
        Boolean hasCharacter = Task6.subsequence(s, t);

        // then
        assertThat(hasCharacter).isTrue();
    }

}
