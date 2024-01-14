package edu.project4;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Renderer {
    private static final Random RANDOM = ThreadLocalRandom.current();
    public static final double X_MIN = -1.0;
    public static final double X_MAX = 1.0;
    public static final double Y_MIN = -1.0;
    public static final double Y_MAX = 1.0;

    private Renderer() {
    }

    @SuppressWarnings("MagicNumber")
    public static Fractal.FractalImage render(
        Fractal.FractalImage canvas,
        List<Fractal.Transformation> affineTransformations,
        List<Fractal.Transformation> nonLinearTransformations,
        int samples,
        int iterPerSample
    ) {

        for (int num = 0; num < samples; ++num) {
            Point pw = randomPoint();

            for (short step = -20; step < iterPerSample; ++step) {
                Fractal.Transformation affineTransformation = randomVariation(affineTransformations);
                Fractal.Transformation nonLinearTransformation = randomVariation(nonLinearTransformations);

                pw = affineTransformation.apply(pw);
                pw = nonLinearTransformation.apply(pw);
                if (step >= 0 && pw.isCorrect(Y_MAX, X_MAX)) {
                    int y = canvas.height - (int) (((Y_MAX - pw.y) / (Y_MAX - Y_MIN)) * canvas.height);
                    int x = canvas.width - (int) (((X_MAX - pw.x) / (X_MAX - X_MIN)) * canvas.width);

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

    @SuppressWarnings("MagicNumber")
    private static Point randomPoint() {
        double x = RANDOM.nextDouble(X_MIN, X_MAX);
        double y = RANDOM.nextDouble(Y_MIN, Y_MAX);
        return new Point(x, y, 255, 255, 255);
    }


    private static Fractal.Transformation randomVariation(List<Fractal.Transformation> variations) {
        return variations.get(RANDOM.nextInt(variations.size()));
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
