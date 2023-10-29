package edu.hw1;


public final class Task1 {

    private Task1() {
    }

    private static final int SEC_IN_MIN = 60;

    public static int minutesToSeconds(String time) {
        if (time == null || time.isEmpty()) {
            return -1;
        }
        int min = Integer.parseInt(time.split(":")[0]);
        int sec = Integer.parseInt(time.split(":")[1]);
        if (sec >= SEC_IN_MIN) {
            return -1;
        }
        return min * SEC_IN_MIN + sec;
    }
}
