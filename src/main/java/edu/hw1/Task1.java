package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
    public static int minutesToSeconds(String time) {
        int min = Integer.parseInt(time.split(":")[0]);
        int sec = Integer.parseInt(time.split(":")[1]);
        if (sec >= 60) {
            return -1;
        }
        return min * 60 + sec;
    }
}
