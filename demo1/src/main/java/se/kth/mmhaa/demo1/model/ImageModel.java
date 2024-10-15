package se.kth.mmhaa.demo1.model;

/**
 * The ImageModel class stores storing and manages pixel data for an image.
 * The pixel data is represented as a 2D array of integers, each integer corresponds to the RGB-value of a pixel
 */
public class ImageModel {
    private int[][] imageData;

    /**
     * Retrieves the image's pixel data.
     *
     * @return a 2D array of integers representing the pixel data of the image.
     * Each integer contains the RGB value for a pixel.
     */
    public int[][] getImageData() {
        return imageData;
    }

    /**
     * Sets the image's pixel data.
     *
     * @param imageData a 2D array of integers where each integer contains the ARGB value for a pixel.
     */
    public void setImageData(int[][] imageData) {
        this.imageData = imageData;
    }
}
