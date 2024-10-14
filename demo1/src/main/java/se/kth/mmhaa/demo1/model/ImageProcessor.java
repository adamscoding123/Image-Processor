package se.kth.mmhaa.demo1.model;


import java.awt.*;

interface ProcessorCallback {
    Color onCycle(int r,int g, int b);
}

public class ImageProcessor implements IProcessor {


    @Override
    public int[][] processImage(int[][] originalImg) {
        return new int[0][];
    }

    public int[][] greyscaleProcessor(int[][] originalImg, double greyStrength) {
       ProcessorCallback callback = new ProcessorCallback() {
           @Override
           public Color onCycle(int r, int g, int b) {
               int gray = (r + g + b) / 3;
               int finalRed = (int) (r * (1 - greyStrength) + gray * greyStrength);
               int finalGreen = (int) (g * (1 - greyStrength) + gray * greyStrength);
               int finalBlue = (int) (b * (1 - greyStrength) + gray * greyStrength);
               return new Color(finalRed,finalGreen,finalBlue);
           }
       };

        return process(originalImg, callback);
    }

    public int[][] contrastProcessor(int[][] originalImg, double contrastStrength) {
        ProcessorCallback callback = new ProcessorCallback() {
            @Override
            public Color onCycle(int r, int g, int b) {
                int finalRed = (int)(((r - 128) * contrastStrength) + 128);
                int finalGreen = (int)(((g - 128) * contrastStrength) + 128);
                int finalBlue = (int)(((b - 128) * contrastStrength) + 128);
                return new Color(finalRed, finalGreen, finalBlue);
            }
        };
        //... logic contrast
        return process(originalImg, callback);
    }


    public int[][] process(int[][] originalImg, ProcessorCallback callback) {
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
