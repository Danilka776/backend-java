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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FractalRenderer {

    private final static Logger LOGGER = LogManager.getLogger();

    private FractalRenderer() {
    }

    @SuppressWarnings({"UncommentedMain", "MagicNumber"})
    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        Fractal.FractalImage fractalImage = Fractal.FractalImage.create(width, height);

        List<Transformation> affineTransformation = getAffineTransformation(20);
        List<Transformation> nonLinearTransformation = new ArrayList<>();
        // Синусоидальное преобразование
//        nonLinearTransformation.add(new SineTransformation((double) 3, (double) 2));
//        nonLinearTransformation.add(new SineTransformation((double) 1, (double) 0));
//        nonLinearTransformation.add(new SineTransformation((double) 3, (double) 1));
        //nonLinearTransformation.add(new SphericalTransformation(0.9));  // Сферическое преобразование
        //nonLinearTransformation.add(new DiskTransformation(0.05));  // Диск преобразование
        nonLinearTransformation.add(new HeartTransformation(0.6));  // Сердце преобразование
        //nonLinearTransformation.add(new SwirlTransformation(1, 1));  // Swirl преобразование
        nonLinearTransformation.add(new HorseshoeTransformation(0.9));  // Horseshoe преобразование
        int samples = 100000;
        int iterPerSample = 200;

        FractalImage renderedImage = Renderer.render(fractalImage, affineTransformation, nonLinearTransformation,
            samples, iterPerSample);

        BufferedImage bufferedImage = createBufferedImage(renderedImage);
        saveImage(bufferedImage,
            "/Users/daniltarasov/backend-java/backend-java/src/main/java/edu/project4/pictures/fractal_image_9.png");
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
            LOGGER.info(e.getMessage());
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
                if ((a * a + d * d < 1)
                    && (b * b + e * e < 1)
                    && (a * a + b * b + d * d + e * e < 1 + (a * e - d * b) * (a * e - d * b))) {
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
