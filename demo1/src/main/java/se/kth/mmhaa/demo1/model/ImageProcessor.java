package se.kth.mmhaa.demo1.model;


import java.awt.*;

interface ProcessorCallback {
    Color onCycle(int r,int g, int b);
}

public class ImageProcessor {

    public static int[][] process(int[][] originalImg, ProcessorCallback callback) {
        int height = originalImg.length;
        int width = originalImg[0].length;
        int[][] img = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = originalImg[y][x];
                int rValue = (pixel >> 16) & 0xff;
                int gValue = (pixel >> 8) & 0xff;
                int bValue = pixel & 0xff;
                Color color = callback.onCycle(rValue, gValue, bValue);
                img[y][x] = (0xff << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
            }
        }
        return img;
    }

}
