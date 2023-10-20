package edu.hw1;


public final class Task2 {
    private Task2() {
    }

    private static final int DIGITS = 10;

    public static int countDigits(Integer num) {
        if (num.equals(0)) {
            return 1;
        }
        int count = 0;
        int tmp = num > 0 ? num : -1 * num;
        while (tmp > 0) {
            count++;
            tmp = tmp / DIGITS;
        }
        return count;
    }
}
