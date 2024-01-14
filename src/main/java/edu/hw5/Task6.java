package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task6 {

    private Task6() {
    }

    public static boolean subsequence(String s, String t) {
        String regex = ".*" + s + ".*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(t);

        return matcher.matches();
    }


}
