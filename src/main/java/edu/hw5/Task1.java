package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Task1 {

    private Task1() {
    }

    public static String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();

        StringBuilder result = new StringBuilder();

        if (days > 0) {
            result.append(days).append("д ");
        }
        if (hours > 0) {
            result.append(hours).append("ч ");
        }
        if (minutes > 0) {
            result.append(minutes).append("м");
        }

        return result.toString();
    }

    public static String period(String[] dates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        Duration sumDuration = Duration.ofSeconds(0);

        for (String date : dates) {
            String regexp = " - ";
            LocalDateTime dateTime1 = LocalDateTime.parse(date.split(regexp)[0], formatter);
            LocalDateTime dateTime2 = LocalDateTime.parse(date.split(regexp)[1], formatter);
            Duration curDuration = Duration.between(dateTime1, dateTime2);
            sumDuration = sumDuration.plus(curDuration);
        }

        return formatDuration(sumDuration.dividedBy(dates.length));
    }
}
