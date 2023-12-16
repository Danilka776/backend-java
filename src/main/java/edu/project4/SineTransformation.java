package edu.project4;

import java.util.Random;

public class SineTransformation implements Fractal.Transformation {
    private final double a;
    private final double b;

    private final Random random = new Random();
    private final int red = random.nextInt(256);
    private final int green = random.nextInt(256);
    private final int blue = random.nextInt(256);

    public SineTransformation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Point apply(Point p) {
        double x = a * Math.sin(p.x);
        double y = b * Math.sin(p.y);

        return new Point(x, y, red, green, blue);
    }

    @Override
    public int getRed() {
        return red;
    }

    @Override
    public int getGreen() {
        return green;
    }

    @Override
    public int getBlue() {
        return blue;
    }
}
