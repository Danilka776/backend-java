package edu.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dictionary {
    private static ArrayList<String> words;
    private static final Random RANDOMIZER = new Random();

    Dictionary() {
        words = new ArrayList<>(Arrays.asList("hello", "human", "apple", "watermelon", "hi"));
    }

    public String getRandomWord(int idx) {
        int index;
        if (idx == -1) {
            index = RANDOMIZER.nextInt(words.size());
        } else {
            index = idx;
        }
        return words.get(index);
    }
}
