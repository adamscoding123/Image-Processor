package se.kth.mmhaa.demo1.model;

import java.awt.*;

/**
 * The {@code ProcessorCallback} interface provides a method for performing pixel-level processing.
 * Implementing classes will define how to process individual red, green, and blue (RGB) values for each pixel.
 */
interface ProcessorCallback {

    /**
     * Processes the red, green, and blue (RGB) values for a single pixel.
     * Implementing this method allows customization of how individual color components are handled during image processing.
     *
     * @param r red component
     * @param g green component
     * @param b blue component
     * @return a Color object representing the processed RGB values for each individual pixel.
     */
    Color onCycle(int r, int g, int b);
}

/**
 * The ImageProcessor class provides a method to process an image by iterating over each pixel and applying a
 * callback for individual pixel-change.
 */
public class ImageProcessor {

    /**
     * Processes the given image by applying ProcessorCallback to each pixel.
     * For each pixel, the red, green, and blue (RGB) components are extracted, processed using the callback, and
     * then compiled into a new processded image
     *
     * @param originalImg the original image represented as a 2D array of  pixels (pixels values).
     *                    Each pixel is expected to be in the (alpha, red, green, blue)-format, exclude alpha here.
     * @param callback    the callback interface used to define how to process each pixel's RGB.
     * @return a 2D array of integer pixel values representing the processed image, in ARGB format.
     */
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
