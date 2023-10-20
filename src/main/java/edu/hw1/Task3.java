package edu.hw1;

import java.util.Arrays;


public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] a, int[] b) {
        int minA = Arrays.stream(a).min().getAsInt();
        int minB = Arrays.stream(b).min().getAsInt();
        int maxA = Arrays.stream(a).max().getAsInt();
        int maxB = Arrays.stream(b).max().getAsInt();
        return minA > minB & maxA < maxB;
    }
}
