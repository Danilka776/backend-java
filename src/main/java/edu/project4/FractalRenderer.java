package edu.project4;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.project4.Fractal.*;

public class FractalRenderer {
    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;
        Fractal.FractalImage fractalImage = Fractal.FractalImage.create(width, height);

        Rect world = new Rect(0, 0, 2.5, 2.5);
        List<Transformation> affineTransformation = new ArrayList<>();
        List<Transformation> nonLinearTransformation = new ArrayList<>();
        affineTransformation.add(new LinearTransformation(0.5, 0, 0, 0.5, 0, 1));  // Линейное преобразование
        affineTransformation.add(new LinearTransformation(0, 1, 0, 0.5, 1, 0));  // Линейное преобразование
        nonLinearTransformation.add(new SineTransformation(2, 1));  // Синусоидальное преобразование
        nonLinearTransformation.add(new SphericalTransformation(2));  // Сферическое преобразование

        int samples = 50000;
        short iterPerSample = 50;
        long seed = System.currentTimeMillis();

        FractalImage renderedImage = Renderer.render(fractalImage, world,affineTransformation, nonLinearTransformation, samples, iterPerSample, seed);

        BufferedImage bufferedImage = createBufferedImage(renderedImage);
        saveImage(bufferedImage, "/Users/daniltarasov/backend-java/backend-java/src/main/java/edu/project4/pictures/fractal_image.png");
    }

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
}
