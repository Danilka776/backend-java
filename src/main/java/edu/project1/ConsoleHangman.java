package edu.project1;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;


public class ConsoleHangman {
    private final Session curSession;

    public ConsoleHangman(int idxWord) throws FileNotFoundException {
        this.curSession = new Session(idxWord);
    }

    public ConsoleHangman() throws FileNotFoundException {
        this.curSession = new Session();
    }

    public ConsoleHangman(String fileName) throws FileNotFoundException {
        try {
            this.curSession = new Session(fileName);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No file");
        }
    }

    public int game() {
        if (curSession.getWord().length() <= 2) {
            return -1;
        } else {
            try {
                run();
            } catch (NoSuchElementException e) {
                return 1;
            }
            return 0;
        }
    }

    @SuppressWarnings({"RegexpSinglelineJava", "MultipleStringLiterals"})
    public void run() {
        System.out.println("Hello, it's Hangman game!");
        System.out.println("If you want to stop the game write: end");
        char guessSymbol = '0';
        while (!curSession.isEndGame()) {
            System.out.println("Guess a letter:");
            Scanner in = new Scanner(System.in);
            String guessInput = in.next();
            if (Objects.equals(guessInput, "end")) {
                curSession.giveUp();
                System.exit(0);
            }
            while (guessInput.length() > 1 || !Character.isDigit(guessInput.charAt(0))) {
                System.out.println("Guess a letter:");
                if (Objects.equals(guessInput, "end")) {
                    curSession.giveUp();
                    System.exit(0);
                }
                guessInput = in.next();
            }
            guessSymbol = guessInput.charAt(0);
            tryGuess(curSession, guessSymbol);
        }
        System.out.println(curSession.guess(guessSymbol).message());
    }

    public GuessResult.State state(char c) {
        if (c == '^') {
            throw new NoSuchElementException("end game!");
        }
        GuessResult res = tryGuess(curSession, c);
        return res.state(c);
    }

    public Session getSession() {
        return curSession;
    }

    @SuppressWarnings({"RegexpSinglelineJava"})
    private GuessResult tryGuess(Session session, char input) {
        GuessResult guessRes = session.guess(input);
        System.out.println(guessRes.message());

        System.out.println();
        System.out.print("The word: ");
        System.out.println(session.getUserAnswer());
        System.out.println();
        return guessRes;
    }

}
