package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Simple input")
    void test1() {
        // given
        String text = "Hello world!";

        // when
        String ciphertext = Task1.atbash(text);

        // then
        assertThat(ciphertext).isEqualTo("Svool dliow!");
    }

    @Test
    @DisplayName("Difficult input")
    void test2() {
        // given
        String text = "Any fool can write code that a computer can understand. " +
            "Good programmers write code that humans can understand. ― Martin Fowler";

        // when
        String ciphertext = Task1.atbash(text);

        // then
        assertThat(ciphertext).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }

    @Test
    @DisplayName("Empty input")
    void test3() {
        // given
        String text = "";

        // when
        String ciphertext = Task1.atbash(text);

        // then
        assertThat(ciphertext).isEqualTo("");
    }

    @Test
    @DisplayName("One char")
    void test4() {
        // given
        String text = "B";

        // when
        String ciphertext = Task1.atbash(text);

        // then
        assertThat(ciphertext).isEqualTo("Y");
    }

    @Test
    @DisplayName("Non char")
    void test5() {
        // given
        String text = "!+3тс";

        // when
        String ciphertext = Task1.atbash(text);

        // then
        assertThat(ciphertext).isEqualTo("!+3тс");
    }

    @Test
    @DisplayName("Null input")
    void test6() {
        // given
        String text = null;

        // when
        String ciphertext = Task1.atbash(text);

        // then
        assertThat(ciphertext).isEqualTo("");
    }
}
