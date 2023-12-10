package edu.project4;

import java.util.Random;

class LinearTransformation implements Fractal.Transformation {
    private final double a, b, c, d, e, f;

    private final Random random = new Random();
    private final int Red = random.nextInt(256);
    private final int Green = random.nextInt(256);
    private final int Blue = random.nextInt(256);

    public LinearTransformation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    @Override
    public Point apply(Point p) {
        double x = a * p.x + b * p.y + e;
        double y = c * p.x + d * p.y + f;
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
