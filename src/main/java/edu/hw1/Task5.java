package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task5 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task5() {
    }

    @SuppressWarnings("MagicNumber")
    private static boolean isPalindrome(Integer n) {
        int r;
        int sum = 0;
        int tmp = n;
        while (tmp > 0) {
            r = tmp % 10;
            sum = sum * 10 + r;
            tmp = tmp / 10;
        }
        return n.equals(sum);
    }

    @SuppressWarnings("MagicNumber")
    public static boolean isPalindromeDescendant(Integer num) {
        Integer tmp = num;
        while (Task2.countDigits(tmp) > 1) {
            if (isPalindrome(tmp)) {
                return true;
            } else {
                int len = Task2.countDigits(tmp);
                if (len % 2 != 1) {
                    char[] str = Integer.toString(tmp).toCharArray();
                    int newTmp = 0;
                    for (var i = 0; i < len; i += 2) {
                        newTmp = newTmp * 10 + (Character.getNumericValue(str[i])
                            + Character.getNumericValue(str[i + 1]));
                    }
                    tmp = newTmp;
                } else {
                    break;
                }
            }
        }
        return false;
    }
}
