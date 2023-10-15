package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task2 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(Integer num) {
        if (num.equals(0)) {
            return 1;
        }
        int count = 0;
        int tmp = num > 0 ? num : -1 * num;
        while (tmp > 0) {
            count++;
            tmp = tmp / 10;
        }
        return count;
    }
}
