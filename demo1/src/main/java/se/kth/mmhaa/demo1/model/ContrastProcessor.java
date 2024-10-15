package se.kth.mmhaa.demo1.model;

import java.awt.*;

/**
 * This class is responsible for processing the original uploaded image
 * - it alters the contrast depending on a strength factor which is taken as an
 * argument
 */
public class ContrastProcessor implements IProcessor {
    double strength;

    public ContrastProcessor() {
        this.strength = 1.0;
    }

    /**
     * Applies a contrast strength to the image
     * 
     * @param strength the strength of the contrast to apply
     */
    public void setStrength(double strength) {
        this.strength = strength;
    }

    @Override
    public int[][] processImage(int[][] originalImg) {
        return ImageProcessor.process(originalImg, (r, g, b) -> {
            int finalRed = (int) (((r - 128) * strength) + 128);
            int finalGreen = (int) (((g - 128) * strength) + 128);
            int finalBlue = (int) (((b - 128) * strength) + 128);
            return new Color(finalRed, finalGreen, finalBlue);
        });
    }

}
