package edu.project4;

import java.util.List;
import java.util.Random;

public class Renderer {
    private static final Random random = new Random();
    public static final double X_MIN = -1.777;
    public static final double X_MAX = 1.777;
    public static final double Y_MIN = -1.0;
    public static final double Y_MAX = 1.0;

    public static Fractal.FractalImage render(
        Fractal.FractalImage canvas,
        Rect world,
        List<Fractal.Transformation> affineTransformations,
        List<Fractal.Transformation> nonLinearTransformations,
        int samples,
        short iterPerSample,
        long seed
    ) {
        random.setSeed(seed);
        double symmetry = 2.0;

        for (int num = 0; num < samples; ++num) {
            Point pw = randomPoint();

            for (short step = -20; step < iterPerSample; ++step) {
                Fractal.Transformation affineTransformation = randomVariation(affineTransformations);
                Fractal.Transformation nonLinearTransformation = randomVariation(nonLinearTransformations);

                Point pwNew = affineTransformation.apply(pw);
                pwNew = nonLinearTransformation.apply(pwNew);
                if (step >= 0 && pwNew.isCorrect(Y_MAX, X_MAX)) {
                    int y = canvas.height - (int) (((Y_MAX - pwNew.y) / (Y_MAX - Y_MIN)) * canvas.height);
                    int x = canvas.width - (int) (((X_MAX - pwNew.x) / (X_MAX - X_MIN)) * canvas.width);

                    if (x < canvas.width && y < canvas.height) {
                        canvas.pixel(x, y).blendColor(affineTransformation.getRed(),
                            affineTransformation.getGreen(),
                            affineTransformation.getBlue()
                        );
                    }
                }

            }
        }

        return canvas;
    }

    private static Point randomPoint() {
        double x = random.nextDouble(X_MIN, X_MAX);
        double y = random.nextDouble(Y_MIN, Y_MAX);
        return new Point(x, y, 255, 255, 255);
    }


    private static Fractal.Transformation randomVariation(List<Fractal.Transformation> variations) {
        return variations.get(random.nextInt(variations.size()));
    }

    private static Point rotate(Point p, double theta) {
        double x = p.x * Math.cos(theta) - p.y * Math.sin(theta);
        double y = p.x * Math.sin(theta) + p.y * Math.cos(theta);
        return new Point(x, y, p.red, p.green, p.blue);
    }

    private static Pixel mapRange(Rect world, Point p, Fractal.FractalImage canvas) {
        int x = (int) ((p.x - world.x) / world.width * canvas.width);
        int y = (int) ((p.y - world.y) / world.height * canvas.height);

        if (canvas.contains(x, y)) {
            return canvas.pixel(x, y);
        }

        return null;
    }
}
