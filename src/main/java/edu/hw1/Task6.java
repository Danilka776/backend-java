package edu.hw1;


public final class Task6 {
    private Task6() {
    }

    private static final int DIGITS = 10;
    private static final int INTERESTING_NUM = 6174;

    private static Integer asc(Integer n) {
        char[] res = Integer.toString(n).toCharArray();
        java.util.Arrays.sort(res);
        int sum = 0;
        for (char re : res) {
            sum = sum * DIGITS + Character.getNumericValue(re);
        }
        return sum;
    }

    private static Integer desc(Integer n) {
        char[] res = Integer.toString(n).toCharArray();
        java.util.Arrays.sort(res);
        int sum = 0;
        for (var i = res.length - 1; i >= 0; i--) {
            sum = sum * DIGITS + Character.getNumericValue(res[i]);
        }
        return sum;
    }

    public static int countK(Integer num) {
        Integer max = desc(num);
        Integer min = asc(num);
        if (max - min != INTERESTING_NUM) {
            return countK(max - min) + 1;
        }
        return 1;
    }
}
