package edu.project1;

import edu.project1.ConsoleHangman;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.NoSuchElementException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Project1Test {
    @Test
    @DisplayName("Get word of length 2")
    void incorrectWordLength() throws FileNotFoundException {
        try {// given
            ConsoleHangman game = new ConsoleHangman(4);

            // when
            int res = game.game();

            // then
            assertThat(res).isEqualTo(-1);
        } catch (Exception e) {
            return;
        }
    }

    @Test
    @DisplayName("The right sequence of actions to defeat")
    void CorrectDefeat() throws FileNotFoundException {
        try {// given word : hello
            ConsoleHangman game = new ConsoleHangman(0);

            GuessResult.State res = game.state('t');
            assertThat(res).isEqualTo(GuessResult.State.Failed);
            res = game.state('t');
            assertThat(res).isEqualTo(GuessResult.State.Failed);
            res = game.state('t');
            assertThat(res).isEqualTo(GuessResult.State.Failed);
            res = game.state('t');
            assertThat(res).isEqualTo(GuessResult.State.Failed);
            res = game.state('t');
            assertThat(res).isEqualTo(GuessResult.State.Failed);
            res = game.state('t');
            assertThat(res).isEqualTo(GuessResult.State.Defeat);
        } catch (Exception e) {
            return;
        }

    }
    @Test
    @DisplayName("The right sequence of actions to win")
    void CorrectWin() throws FileNotFoundException {
        try {// given word : hello
            ConsoleHangman game = new ConsoleHangman(0);

            GuessResult.State res = game.state('l');
            assertThat(res).isEqualTo(GuessResult.State.Success);
            res = game.state('t');
            assertThat(res).isEqualTo(GuessResult.State.Failed);
            res = game.state('o');
            assertThat(res).isEqualTo(GuessResult.State.Success);
            res = game.state('h');
            assertThat(res).isEqualTo(GuessResult.State.Success);
            res = game.state('t');
            assertThat(res).isEqualTo(GuessResult.State.Failed);
            res = game.state('e');
            assertThat(res).isEqualTo(GuessResult.State.Success);
            res = game.state('e');
            assertThat(res).isEqualTo(GuessResult.State.Win);
        } catch (Exception e) {
            return;
        }
    }

    @Test
    @DisplayName("Stop game by CTRL+D")
    void prematureStop() throws FileNotFoundException {
        try {// give
            ConsoleHangman game = new ConsoleHangman();

            // when
            try {
                GuessResult.State res = game.state('^'); // ctrl+d
            } catch (NoSuchElementException e) {
                // then
                assertThat(e.getMessage()).isEqualTo("end game!");
            }
        } catch (Exception e) {
            return;
        }
    }

    @Test
    @DisplayName("Give incorrect file name")
    void NoFile() throws FileNotFoundException {
        try {// give
            String fileName = "../wrongFileName";

            // when
            try {
                ConsoleHangman game = new ConsoleHangman(fileName);
            } catch (FileNotFoundException e) {
                // then
                assertThat(e.getMessage()).isEqualTo("No file");
            }
        } catch (Exception e) {
            return;
        }
    }

}
