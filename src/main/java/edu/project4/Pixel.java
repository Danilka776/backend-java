package edu.project4;

public class Pixel {
    public int r;
    public int g;
    public int b;
    public int hitCount;

    public Pixel(int r, int g, int b, int hitCount) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
    }

    public void blendColor(int newR, int newG, int newB) {
        if (hitCount == 0) {
            // Если точка попала в этот пиксель впервые, просто присвоим ей новый цвет
            r = newR;
            g = newG;
            b = newB;
        } else {
            // Если точка уже попадала в этот пиксель, выполним смешивание цветов
            r = (r + newR) / 2;
            g = (g + newG) / 2;
            b = (b + newB) / 2;
        }

        // Увеличиваем счетчик попаданий в пиксель
        hitCount++;
        //return new Pixel(r, g, b, hitCount);
    }
}


