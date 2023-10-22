package edu.project1;

import org.jetbrains.annotations.NotNull;

public class Session {
    private static String word = new Dictionary().getRandomWord(0);

    public Session(int idx) {
        word = new Dictionary().getRandomWord(idx);
    }

    public Session() {
        word = new Dictionary().getRandomWord(-1);
    }

    private static char[] userAnswer = new Word(word).getCurrentWord();
    private final static int MAX_MISTAKES = 5;
    private static int attempts = 0;
    private boolean endGame = false;
    private boolean wonGame = false;

    public void setEndGame() {
        endGame = true;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setWon() {
        wonGame = true;
    }

    public boolean isWonGame() {
        return wonGame;
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }

    public void incAttempts() {
        this.attempts++;
    }

    public int getAttempst() {
        return attempts;
    }

    public int getMaxAttempst() {
        return MAX_MISTAKES;
    }

    public String getWord() {
        return word;
    }

    public void changeWord(char symbol) {
        char[] wordArray = word.toCharArray();
        int i = 0;
        for (char c : wordArray) {
            if (c == symbol) {
                userAnswer[i] = c;
            }
            i++;
        }
    }

    public boolean isSymbolInWord(char symbol) {
        for (char c : word.toCharArray()) {
            if (c == symbol) {
                return true;
            }
        }
        return false;
    }

    @NotNull GuessResult guess(char guess) {
        GuessResult res;
        if (isSymbolInWord(guess)) {
            if (isWonGame()) {
                res = new GuessResult.Win(this);
                res.state(guess);
            } else {
                res = new GuessResult.SuccessfulGuess(this);
                res.state(guess);
            }
        } else {
            if (isEndGame()) {
                res = new GuessResult.Defeat(this);
                res.state(guess);
            } else {
                res = new GuessResult.FailedGuess(this);
                res.state(guess);
            }

        }
        return res;
    }

    @NotNull void giveUp() {
        setEndGame();
    }
}
