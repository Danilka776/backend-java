package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import java.util.OptionalInt;

public final class Task3 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task3() {
    }

    @SuppressWarnings("MagicNumber")
    public static boolean isNestable(int[] a, int[] b) {
        int minA = Arrays.stream(a).min().getAsInt();
        int minB = Arrays.stream(b).min().getAsInt();
        int maxA = Arrays.stream(a).max().getAsInt();
        int maxB = Arrays.stream(b).max().getAsInt();
        return minA > minB & maxA < maxB;
    }
}
