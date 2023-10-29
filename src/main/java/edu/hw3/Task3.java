package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Task3 {

    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> words) {
        Map<T, Integer> frequencies = new HashMap<T, Integer>();
        for (T word : words) {
            if (frequencies.containsKey(word)) {
                frequencies.put(word, frequencies.get(word) + 1);
            } else {
                frequencies.put(word, 1);
            }
        }
        return frequencies;
    }

}
