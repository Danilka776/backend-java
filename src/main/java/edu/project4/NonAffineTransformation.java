package edu.project4;

import java.util.Random;

class NonAffineTransformation implements Fractal.Transformation {

    private final Random random = new Random();
    private final int Red = random.nextInt(256);
    private final int Green = random.nextInt(256);
    private final int Blue = random.nextInt(256);
    //private final int Red = 0;
    //private final int Green = 144;
    //private final int Blue = 15;


    public NonAffineTransformation() {
    }

    @Override
    public Point apply(Point p) {
        return p;
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


