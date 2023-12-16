package edu.project4;

import edu.project4.Fractal.FractalImage;
import edu.project4.Fractal.Transformation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;


public class FractalRenderer {

    private FractalRenderer() {
    }

    @SuppressWarnings({"UncommentedMain", "MagicNumber"})
    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        Fractal.FractalImage fractalImage = Fractal.FractalImage.create(width, height);

        List<Transformation> affineTransformation = getAffineTransformation(8);
        List<Transformation> nonLinearTransformation = new ArrayList<>();
        //affineTransformation.add(new LinearTransformation(0.5, 0, 0, 0.5, 0.8, 1));  // Линейное преобразование
        //affineTransformation.add(new LinearTransformation(0, 0.9, 0, 0.5, 0, 0));  // Линейное преобразование
        nonLinearTransformation.add(new SineTransformation((double) 1920 /2, (double) 1080 /2));  // Синусоидальное преобразование
        nonLinearTransformation.add(new SphericalTransformation(0.1));  // Сферическое преобразование
        nonLinearTransformation.add(new DiskTransformation(0.05));  // Диск преобразование
        nonLinearTransformation.add(new HeartTransformation(0.5));  // Сердце преобразование
        //nonLinearTransformation.add(new NonAffineTransformation());
        int samples = 100000;
        int iterPerSample = 200;
        long seed = System.currentTimeMillis();

        FractalImage renderedImage = Renderer.render(fractalImage, affineTransformation, nonLinearTransformation,
            samples, iterPerSample, seed);

        BufferedImage bufferedImage = createBufferedImage(renderedImage);
        saveImage(bufferedImage,
            "/Users/daniltarasov/backend-java/backend-java/src/main/java/edu/project4/pictures/fractal_image_8.png");
    }

    @SuppressWarnings("MagicNumber")
    private static BufferedImage createBufferedImage(FractalImage image) {
        BufferedImage bufferedImage = new BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < image.height; y++) {
            for (int x = 0; x < image.width; x++) {
                Pixel pixel = image.pixel(x, y);
                int rgb = (pixel.r << 16) | (pixel.g << 8) | pixel.b;
                bufferedImage.setRGB(x, y, rgb);
            }
        }

        return bufferedImage;
    }

    private static void saveImage(BufferedImage image, String filename) {
        try {
            File outputfile = new File(filename);
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Transformation> getAffineTransformation(int numTransformations) {
        List<Transformation> affineTransformation = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < numTransformations; i++) {
            boolean cheack = false;
            double a = random.nextDouble(1);
            double b = random.nextDouble(1);
            double c = random.nextDouble(1);
            double d = random.nextDouble(1);
            double e = random.nextDouble(1);
            double f = random.nextDouble(1);
            while (!cheack) {
                if ((a * a + d * d < 1) &&
                    (b * b + e * e < 1) &&
                    (a * a + b * b + d * d + e * e < 1 + (a * e - d * b) * (a * e - d * b))) {
                    cheack = true;
                }
                a = random.nextDouble(1);
                b = random.nextDouble(1);
                c = random.nextDouble(1);
                d = random.nextDouble(1);
                e = random.nextDouble(1);
                f = random.nextDouble(1);
            }
            affineTransformation.add(new LinearTransformation(a, b, c, d, e, f));
        }


        return affineTransformation;
    }
}
