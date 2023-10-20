package edu.hw1;


public final class Task5 {
    private Task5() {
    }

    private static final int DIGITS = 10;

    private static boolean isPalindrome(Integer n) {
        int remainder;
        int sum = 0;
        int tmp = n;
        while (tmp > 0) {
            remainder = tmp % DIGITS;
            sum = sum * DIGITS + remainder;
            tmp = tmp / DIGITS;
        }
        return n.equals(sum);
    }

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
                        newTmp = newTmp * DIGITS + (Character.getNumericValue(str[i])
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
