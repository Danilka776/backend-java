package edu.hw1;


public final class Task7 {
    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        int numLen = Integer.toBinaryString(n).length();
        int leftIdx = (int) Math.pow(2, numLen - 1);
        int ans = n;
        int newShift = shift % numLen;
        for (int i = 0; i < newShift; i++) {
            int buff = ans / leftIdx;
            ans = ((ans % leftIdx) << 1) + buff;
        }
        return ans;
    }

    public static int rotateRight(int n, int shift) {
        int numLen = Integer.toBinaryString(n).length();
        int leftIdx = (int) Math.pow(2, numLen - 1);
        int ans = n;
        int newShift = shift % numLen;
        for (int i = 0; i < newShift; i++) {
            int buff = ans % 2;
            ans = (ans >> 1) + buff * leftIdx;
        }
        return ans;
    }
}
