package edu.project1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;


public sealed interface GuessResult {
    String state(char c);

    int attempt();

    int maxAttempts();

    @NotNull String message();

    record Defeat(Session s) implements GuessResult {
        @Override
        public String state(char c) {
            return "Defeat";
        }

        @Override
        public int attempt() {
            return s.getAttempst();
        }

        @Override
        public int maxAttempts() {
            return s.getMaxAttempst();
        }

        @Override
        public @NotNull String message() {
            return "You lost!";
        }
    }

    record Win(Session s) implements GuessResult {
        @Override
        public String state(char c) {
            s.setWon();
            return "Win";
        }

        @Override
        public int attempt() {
            return s.getAttempst();
        }

        @Override
        public int maxAttempts() {
            return s.getMaxAttempst();
        }

        @Override
        public @NotNull String message() {
            return "You won!";
        }
    }

    record SuccessfulGuess(Session s) implements GuessResult {
        @Override
        public String state(char c) {
            s.changeWord(c);
            if (Arrays.equals(s.getUserAnswer(), s.getWord().toCharArray())) {
                s.setWon();
                s.setEndGame();
            }
            return "Success";
        }

        @Override
        public int attempt() {
            return s.getAttempst();
        }

        @Override
        public int maxAttempts() {
            return s.getMaxAttempst();
        }

        @Override
        public @NotNull String message() {
            return "Hit!";
        }
    }

    record FailedGuess(Session s) implements GuessResult {
        @Override
        public String state(char c) {
            return "Failed";
        }

        @Override
        public int attempt() {
            s.incAttempts();
            if (s.getAttempst() == s.getMaxAttempst()) {
                s.setEndGame();
            }
            return s.getAttempst();
        }

        @Override
        public int maxAttempts() {
            return s.getMaxAttempst();
        }

        @Override
        public @NotNull String message() {
            return "Missed, mistake " + attempt() + " out of " + maxAttempts() + ".";
        }
    }
}
