package edu.project4;

public class Fractal {

    public interface Transformation {
        Point apply(Point p);

        int getRed();

        int getGreen();

        int getBlue();
    }


    public static class FractalImage {
        private final Pixel[][] data;
        final int width;
        final int height;

        public FractalImage(Pixel[][] data, int width, int height) {
            this.data = data;
            this.width = width;
            this.height = height;
        }

        public static FractalImage create(int width, int height) {
            Pixel[][] data = new Pixel[width][height];
            for (int i = 0; i < width; ++i) {
                for (int j = 0; j < height; ++j) {
                    data[i][j] = new Pixel(0, 0, 0, 0); // черные пиксели
                }
            }

            return new FractalImage(data, width, height);
        }

        public boolean contains(int x, int y) {
            return x >= 0 && x < width && y >= 0 && y < height;
        }

        public Pixel pixel(int x, int y) {
            return data[x][y];
        }

        // Добавьте методы для работы с изображением, например, для гамма-коррекции
    }

}
