package edu.project4;

import java.util.Random;

public class DiskTransformation implements Fractal.Transformation {

    private final Random random = new Random();
    private final int red = random.nextInt(256);
    private final int green = random.nextInt(256);
    private final int blue = random.nextInt(256);

    private final double a;

    public DiskTransformation(double a) {
        this.a = a;
    }

    @Override
    public Point apply(Point p) {
        double r = Math.sqrt(p.x * p.x + p.y * p.y);
        double arctg = Math.atan(p.y / p.x);

        double x = a * (1 / Math.PI) * arctg * Math.sin(Math.PI * r);
        double y = a * (1 / Math.PI) * arctg * Math.cos(Math.PI * r);

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
