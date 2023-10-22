package edu.project1;

import edu.project1.ConsoleHangman;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Project1Test {
    @Test
    @DisplayName("Incorrect word length")
    void test1() {
        // given
        ConsoleHangman game = new ConsoleHangman(4);

        // when
        int res = game.game();

        // then
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("Correct defeat")
    void test2() {
        // given word : hello
        ConsoleHangman game = new ConsoleHangman(0);

        String res = game.state('t');
        assertThat(res).isEqualTo("Failed");
        res = game.state('t');
        assertThat(res).isEqualTo("Failed");
        res = game.state('t');
        assertThat(res).isEqualTo("Failed");
        res = game.state('t');
        assertThat(res).isEqualTo("Failed");
        res = game.state('t');
        assertThat(res).isEqualTo("Failed");
        res = game.state('t');
        assertThat(res).isEqualTo("Defeat");

    }
    @Test
    @DisplayName("Correct game")
    void test3() {
        // given word : hello
        ConsoleHangman game = new ConsoleHangman(0);

        String res = game.state('l');
        assertThat(res).isEqualTo("Success");
        res = game.state('t');
        assertThat(res).isEqualTo("Failed");
        res = game.state('o');
        assertThat(res).isEqualTo("Success");
        res = game.state('h');
        assertThat(res).isEqualTo("Success");
        res = game.state('t');
        assertThat(res).isEqualTo("Failed");
        res = game.state('e');
        assertThat(res).isEqualTo("Success");
        res = game.state('e');
        assertThat(res).isEqualTo("Win");

    }

}
