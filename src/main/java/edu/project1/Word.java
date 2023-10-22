package edu.project1;

public class Word {
    private final char[] currentWord;

    public Word(String word) {
        currentWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            currentWord[i] = '*';
        }
    }

    public char[] getCurrentWord() {
        return currentWord;
    }
}
