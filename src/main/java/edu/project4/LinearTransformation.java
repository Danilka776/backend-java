package edu.project4;

import java.util.Random;

class LinearTransformation implements Fractal.Transformation {
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;


    private final Random random = new Random();
    private final int red = random.nextInt(256);
    private final int green = random.nextInt(256);
    private final int blue = random.nextInt(256);
    //private final int Red = 0;
    //private final int Green = 144;
    //private final int Blue = 15;


    LinearTransformation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    @Override
    public Point apply(Point p) {
        double x = a * p.x + b * p.y + c;
        double y = d * p.x + e * p.y + f;
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
