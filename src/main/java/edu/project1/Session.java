package edu.project1;

import java.io.FileNotFoundException;
import org.jetbrains.annotations.NotNull;

public class Session {
    private String word = "test";
    private final char[] userAnswer;

    private static final String FILE_NAME = "../wordsForGame";

    public Session(int idx) throws FileNotFoundException {
        word = new Dictionary(FILE_NAME).getRandomWord(idx);
        userAnswer = new Word(word).getCurrentWord();
    }

    public Session(String fileName) throws FileNotFoundException {
        try {
            word = new Dictionary(fileName).getRandomWord(-1);
            userAnswer = new Word(word).getCurrentWord();
            maxMistake = Dictionary.getMaxMistake();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    public Session() throws FileNotFoundException {
        word = new Dictionary(FILE_NAME).getRandomWord(-1);
        userAnswer = new Word(word).getCurrentWord();
        maxMistake = Dictionary.getMaxMistake();
    }


    private int maxMistake = 1; // Default
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
        return maxMistake;
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
