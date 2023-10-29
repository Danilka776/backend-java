package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {
    }


    public static List<String> clusterize(String str) {
        int checker = 0;
        List<String> cluster = new ArrayList<String>();
        String curBrackets = "";
        if (str != null && !str.isEmpty()) {
            for (Character c : str.toCharArray()) {
                if (checker < 0) {
                    return new ArrayList<String>();
                }
                if (c.equals('(')) {
                    checker++;
                } else if (c.equals(')')){
                    checker--;
                } else {
                    return new ArrayList<String>();
                }
                curBrackets += c;
                if (checker == 0) {
                    cluster.add(curBrackets);
                    curBrackets = "";
                }
            }
            return cluster;
        } else if (str != null) {  // empty input
            cluster.add("");
        }
        return cluster;
    }

}
