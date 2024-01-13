package edu.project4;

public class Point {
    final int red;
    final int green;
    final int blue;
    final double x;
    final double y;

    public Point(double x, double y, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    public double[] getPoint() {
        return new double[]{x, y};
    }

    public int[] getColor() {
        return new int[]{red, green, blue};
    }

    public boolean isCorrect(double width, double height) {
        return x >= -width && x < width && y >= -height && y < height;
    }
}
