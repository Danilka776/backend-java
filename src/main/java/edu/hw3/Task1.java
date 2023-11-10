package edu.hw3;

public class Task1 {

    private Task1() {
    }

    private static char findChar(char c) {
        char res = c;
        if (c >= 'a' && c <= 'z') {
            res = (char) ('z' - (c - 'a'));
        } else if (c >= 'A' && c <= 'Z') {
            res = (char) ('Z' - (c - 'A'));
        }
        return res;
    }

    public static String atbash(String text) {
        String ciphertext = "";
        if (text != null && !text.isEmpty()) {
            for (char c : text.toCharArray()) {
                ciphertext += findChar(c);
            }
        }
        return ciphertext;
    }

}
