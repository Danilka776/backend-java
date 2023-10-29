package edu.hw1;


public final class Task4 {
    private Task4() {
    }

     static String fixString(String str) {
        String res = "";
        char[] array = str.toCharArray();
        for (var i = 1; i < str.length(); i += 2) {
            res = res + array[i] + array[i - 1];
        }
        if (res.length() < str.length()) {
            res = res + array[str.length() - 1];
        }
        return res;
    }
}
