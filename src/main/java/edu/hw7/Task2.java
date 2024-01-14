package edu.hw7;

import java.util.stream.IntStream;

public class Task2 {

    private Task2() {
    }

    public static int factorial(int number) {
        if (number <= 1) {
            return 1;
        } else {
            return IntStream.rangeClosed(1, number).parallel().reduce((x, y) -> x * y).getAsInt();
        }
    }


}
