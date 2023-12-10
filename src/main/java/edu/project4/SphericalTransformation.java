package edu.project4;

import java.util.Random;

public class SphericalTransformation implements Fractal.Transformation {
    private final double scale;

    private final Random random = new Random();
    private final int red = random.nextInt(256);
    private final int green = random.nextInt(256);
    private final int blue = random.nextInt(256);

    public SphericalTransformation(double scale) {
        this.scale = scale;
    }

    @Override
    public Point apply(Point p) {
        double r = Math.sqrt(p.x * p.x + p.y * p.y);
        double theta = Math.atan2(p.y, p.x);
        double newR = scale * Math.sin(r);
        double x = newR * Math.cos(theta);
        double y = newR * Math.sin(theta);

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
