package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {
    }

    private static List<String> getBrackets(String str) {
        int checker = 0;
        String curBrackets = "";
        List<String> cluster = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            if (checker < 0) {
                return new ArrayList<>();
            }
            if (c.equals('(')) {
                checker++;
            } else if (c.equals(')')) {
                checker--;
            } else {
                return new ArrayList<>();
            }
            curBrackets += c;
            if (checker == 0) {
                cluster.add(curBrackets);
                curBrackets = "";
            }
        }
        return cluster;
    }

    public static List<String> clusterize(String str) {
        List<String> cluster = new ArrayList<>();
        if (str == null) {
            return cluster;
        }
        if (!str.isEmpty()) {
            cluster = getBrackets(str);
        } else {  // empty input
            cluster.add("");
        }
        return cluster;
    }

}
