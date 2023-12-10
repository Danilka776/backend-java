package edu.project4;

import java.util.Random;

public class SineTransformation implements Fractal.Transformation {
    private final double a, b;

    private final Random random = new Random();
    private final int Red = random.nextInt(256);
    private final int Green = random.nextInt(256);
    private final int Blue = random.nextInt(256);

    public SineTransformation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Point apply(Point p) {
        double x = a * Math.sin(p.x);
        double y = b * Math.sin(p.y);

        return new Point(x, y, Red, Green, Blue);
    }
    @Override
    public int getRed() {
        return Red;
    }
    @Override
    public int getGreen() {
        return Green;
    }
    @Override
    public int getBlue() {
        return Blue;
    }
}
