package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task4 {

    private Task4() {
    }


    public static String convertToRoman(Integer num) {
        if (num < 0 || num > 3999) {
            return null;
        }
        List<String> thousands = new ArrayList<String>(Arrays.asList("", "M", "MM", "MMM"));
        List<String> hundreds = new ArrayList<String>(Arrays.asList("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"));
        List<String> tens = new ArrayList<String>(Arrays.asList("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"));
        List<String> units = new ArrayList<String>(Arrays.asList("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"));

        return thousands.get(num / 1000) + hundreds.get((num % 1000) / 100) +
            tens.get((num % 100) / 10) + units.get(num % 10);
    }

}
