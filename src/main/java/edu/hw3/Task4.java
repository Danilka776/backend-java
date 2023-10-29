package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task4 {

    private Task4() {
    }

    private static final int MAX_NUM = 3999;
    private static final int DEC = 10;
    private static final int HUNDRED = 100;
    private static final int THOUSAND = 1000;

    public static String convertToRoman(Integer num) {
        if (num < 0 || num > MAX_NUM) {
            return null;
        }
        List<String> thousands = new ArrayList<String>(Arrays.asList("", "M", "MM", "MMM"));
        List<String> hundreds = new ArrayList<String>(Arrays.asList(
            "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"));
        List<String> tens = new ArrayList<String>(Arrays.asList(
            "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"));
        List<String> units = new ArrayList<String>(Arrays.asList(
            "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"));

        return thousands.get(num / THOUSAND) + hundreds.get((num % THOUSAND) / HUNDRED)
            + tens.get((num % HUNDRED) / DEC) + units.get(num % DEC);
    }

}
