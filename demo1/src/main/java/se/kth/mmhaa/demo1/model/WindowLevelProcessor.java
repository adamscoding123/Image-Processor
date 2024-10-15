package se.kth.mmhaa.demo1.model;

import java.awt.*;

/**
 * The WindowLevelProcessor class processes an image by adjusting its
 * contrast and brightness using window leveling techniques.
 * It alters the contrast depending on a strength factor, which is taken as an
 * argument.
 */
public class WindowLevelProcessor implements IProcessor {
    private int level;
    private int window;

    /**
     * Constructs a new WindowLevelProcessor with default window and level values.
     * The default level is set to 1, and the window is set to 1 initially
     */
    public WindowLevelProcessor() {
        this.level = 1;
        this.window = 1;
    }

    /**
     * Sets the contrast strength level for the processor.
     *
     * @param level the strength of levelling to apply, where higher values
     *              represent stronger levelling.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Sets the window size for the processor, which controls the range of pixel
     * intensities.
     *
     * @param window the window range to apply, controlling the contrast range.
     */
    public void setWindow(int window) {
        this.window = window;
    }

    /**
     * Processes the given image by applying window leveling to each pixel.
     * The contrast and brightness are adjusted based on the specified window and
     * level settings.
     *
     * @param image the original image represented as a 2D array of pixel values.
     * @return a new 2D array representing the processed image with adjusted
     *         contrast and brightness.
     */
    @Override
    public int[][] processImage(int[][] image) {
        return ImageProcessor.process(image, (r, g, b) -> {
            int low = level - window / 2;
            int high = level + window / 2;
            int finalRed = windowLevelToPixel(r, low, high);
            int finalGreen = windowLevelToPixel(g, low, high);
            int finalBlue = windowLevelToPixel(b, low, high);
            return new Color(finalRed, finalGreen, finalBlue);
        });
    }

    /**
     * Adjusts a pixel's intensity based on the window and level settings.
     * Pixels outside the window range are clamped to 0 or 255, while pixels within
     * the range are linearly scaled between 0 and 255.
     *
     * @param pixel the original pixel intensity value.
     * @param low   the lower bound of the window.
     * @param high  the upper bound of the window.
     * @return the adjusted pixel value, clamped between 0 and 255.
     */
    private int windowLevelToPixel(int pixel, int low, int high) {
        if (pixel < low) {
            return 0;
        } else if (pixel > high) {
            return 255;
        } else {
            return (int) (((double) (pixel - low) / (high - low)) * 255);
        }
    }
}
