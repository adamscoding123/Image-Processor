package se.kth.mmhaa.demo1.model;

import java.awt.*;

public class ContrastProcessor implements IProcessor {
    double strength;

    public ContrastProcessor() {
        this.strength = 1.0;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }
    @Override
    public int[][] processImage(int[][] originalImg) {
        ProcessorCallback callback = new ProcessorCallback() {
            @Override
            public Color onCycle(int r, int g, int b) {
                int finalRed = (int)(((r - 128) * strength) + 128);
                int finalGreen = (int)(((g - 128) * strength) + 128);
                int finalBlue = (int)(((b - 128) * strength) + 128);
                return new Color(finalRed, finalGreen, finalBlue);
            }
        };
        return ImageProcessor.process(originalImg, callback);
    }


}
