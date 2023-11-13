package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class Task3 {

    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        DateTimeFormatter[] dateFormatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-d"),
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yy")
        };

        for (DateTimeFormatter dateFormatter : dateFormatters) {
            try {
                LocalDate date = LocalDate.parse(string, dateFormatter);
                return Optional.of(date);
            } catch (Exception ignored) {
            }
        }
        Optional<LocalDate> res = Optional.empty();
        String curDate = "2023-11-13";
        if (string.equalsIgnoreCase("tomorrow")) {
            res = Optional.of(LocalDate.parse(curDate).plusDays(1));
        } else if (string.equalsIgnoreCase("today")) {
            res = Optional.of(LocalDate.parse(curDate));
        } else if (string.equalsIgnoreCase("yesterday")) {
            res = Optional.of(LocalDate.parse(curDate).minusDays(1));
        } else if (string.matches("\\d+\\s+day[s]?\\s+ago")) {
            int daysAgo = Integer.parseInt(string.split("\\s+")[0]);
            res = Optional.of(LocalDate.parse(curDate).minusDays(daysAgo));
        }

        return res;
    }
}

