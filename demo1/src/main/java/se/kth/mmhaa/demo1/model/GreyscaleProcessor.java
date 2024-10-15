package se.kth.mmhaa.demo1.model;

import java.awt.*;

/**
 * This class is responsible for processing the original uploaded image
 * - it alters the greyscaling depending on a strength factor which is taken as
 * an argument
 */
public class GreyscaleProcessor implements IProcessor {
    double strength;

    public GreyscaleProcessor() {
        this.strength = 1.0;
    }

    /**
     * Applies greyscaling strength to the image
     * 
     * @param strength the strength of the contrast to apply
     */

    public void setStrength(double strength) {
        this.strength = strength;
    }

    @Override
    public int[][] processImage(int[][] originalImg) {
        return ImageProcessor.process(originalImg, (r, g, b) -> {
            int gray = (r + g + b) / 3;
            int finalRed = (int) (r * (1 - strength) + gray * strength);
            int finalGreen = (int) (g * (1 - strength) + gray * strength);
            int finalBlue = (int) (b * (1 - strength) + gray * strength);
            return new Color(finalRed, finalGreen, finalBlue);
        });
    }

}
