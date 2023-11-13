package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class Task2 {

    private Task2() {
    }

    private static final int DAY_OF_MONTH = 13;

    public static String[] getFridays13th(int year) {
        LocalDate date = LocalDate.of(year, 1, DAY_OF_MONTH);
        List<String> dates = new ArrayList<>();

        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                dates.add(date.toString());
            }
            date = date.plusMonths(1);
        }
        return dates.toArray(new String[0]);
    }

    public static LocalDate getNextFridayThe13th(LocalDate date) {
        LocalDate firstFridayOfNextMonth = date.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
        return (firstFridayOfNextMonth.getDayOfMonth() == DAY_OF_MONTH) ? firstFridayOfNextMonth
            : firstFridayOfNextMonth.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    }


}
