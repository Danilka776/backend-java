package edu.hw11;


public final class Task2 {

    private Task2() {
    }

    public interface Function {

        int sum(int a, int b);
    }

    static class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    static class ArithmeticUtilsNew {
        public int sum(int a, int b) {
            return a * b;
        }
    }

}
