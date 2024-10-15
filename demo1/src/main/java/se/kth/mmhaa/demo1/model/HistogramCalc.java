package se.kth.mmhaa.demo1.model;

/**
 * The {@code HistogramCalc} class provides a method to calculate the histogram of an image.
 * The histogram represents the distribution of pixel intensities for the red, green, and blue color channels.
 */
public class HistogramCalc {

    /**
     * Calculates the histogram for an image's data. The histogram represents the intensities
     * for the red, green, and blue color channels, with values ranging from 0 to 255 (256 elements of array).
     *
     * @param imageData a 2D array of integers representing the pixel data of an image, where each integer contains
     *                  the RGB value for a pixel.
     * @return a 2D array representing the histogram data. The 1st dimension represents the color channels.
     * 2nd dimension represents the intensity.
     */
    public static int[][] calculateHistogram(int[][] imageData) {
        int[][] histogram = new int[3][256]; // 3 colors (Red, Green, Blue) and 256 intensity levels for each.

        // Iterate over each pixel in the image.
        for (int[] row : imageData) {
            for (int pixel : row) {
                // Extract the red, green, and blue intensities from the pixel.
                int rValue = ((pixel >> 16) & 0xff); // red
                int gValue = ((pixel >> 8) & 0xff);  // green
                int bValue = pixel & 0xff;           // blue

                // Update the histogram for each color channel.
                histogram[0][rValue]++;  // Red channel
                histogram[1][gValue]++;  // Green channel
                histogram[2][bValue]++;  // Blue channel
            }
        }

        return histogram; // Return the histogram data.
    }
}
