package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task4 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task4() {
    }

    @SuppressWarnings("MagicNumber")
    public static String fixString(String str) {
        String res = "";
        char[] array = str.toCharArray();
        for (var i = 1; i < str.length(); i += 2) {
            res = res + array[i] + array[i - 1];
        }
        if (res.length() < str.length()) {
            res = res + array[str.length() - 1];
        }
        return res;
    }
}
