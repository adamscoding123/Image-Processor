package se.kth.mmhaa.demo1.model;

import java.awt.*;

public class WindowLevelProcessor implements IProcessor{
    int level;
    int window;

    public WindowLevelProcessor(){
        this.level=1;
        this.window=1;
    }

    public void setLevel(int level){
        this.level = level;
    }
    public void setWindow(int window){
        this.window = window;
    }


    public int[][] processImage(int[][] image){
        ProcessorCallback callback = new ProcessorCallback() {
            @Override
            public Color onCycle(int r, int g, int b) {
                int low = level-window/2;
                int high = level+window/2;
                int finalRed = windowLevelToPixel(r,low,high);
                int finalGreen = windowLevelToPixel(g, low, high);
                int finalBlue = windowLevelToPixel(b, low, high);
                return new Color(finalRed, finalGreen, finalBlue);
            }
        };
        return ImageProcessor.process(image, callback);
    }

    private int windowLevelToPixel(int pixel, int low, int high) {
        if (pixel < low) {
            return 0;
        } else if (pixel > high) {
            return 255;
        } else {
            return (int) (((double)(pixel - low) / (high - low)) * 255);
        }
    }
}
