package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task6 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task6() {
    }

    @SuppressWarnings("MagicNumber")
    private static Integer asc(Integer n) {
        char[] res = Integer.toString(n).toCharArray();
        java.util.Arrays.sort(res);
        int sum = 0;
        for (var i = 0; i < res.length; i++) {
            sum = sum * 10 + Character.getNumericValue(res[i]);
        }
        return sum;
    }

    @SuppressWarnings("MagicNumber")
    private static Integer desc(Integer n) {
        char[] res = Integer.toString(n).toCharArray();
        java.util.Arrays.sort(res);
        int sum = 0;
        for (var i = res.length - 1; i >= 0; i--) {
            sum = sum * 10 + Character.getNumericValue(res[i]);
        }
        return sum;
    }

    @SuppressWarnings("MagicNumber")
    public static int k(Integer num) {
        Integer max = desc(num);
        Integer min = asc(num);
        if (max - min != 6174) {
            return k(max - min) + 1;
        }
        return 1;
    }
}
