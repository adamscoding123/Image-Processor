package se.kth.mmhaa.demo1.model;

/**
 * The {@code IProcessor} interface defines the method required for processing an image.
 * Implementing classes must provide implementation of this interface IProcessor.
 */
public interface IProcessor {

    /**
     * Processes the original image and returns the modified image.
     * Implementations of this method should define how the image is changed (typ greyscaled, contrasted)
     *
     * @param originalImg the original image represented as a 2D array of pixel values.
     * @return the processed image, also represented as a 2D array of pixel values.
     */
    public abstract int[][] processImage(int[][] originalImg);
}
