package se.kth.mmhaa.demo1.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * The FileIO class provides utility methods for reading, writing, and converting images.
 * It supports converting between Image and pixel arrays, as well as saving images to files in various formats.
 */
public class FileIO {


    /**
     * Reads an image from a given file and returns it as a {@code Image} object.
     * @param file the file from which to read the image
     * @return the Image object read from the file
     */
    public static Image readImage(File file) {
        return new Image(file.toURI().toString());
    }

    /**
     * Saves the given pixel array as an image file. The file format is determined by the file extension (.png).
     * @param file  the file that the image will be saved to
     * @param image the pixel array representing the image to be saved
     */
    public static void saveImage(File file, int[][] image) {
        WritableImage writableImage = pixelArrayToImage(image);
        BufferedImage bufferedImage = convertToBufferedImage(writableImage);
        saveBufferedImageToFile(bufferedImage, file);
    }

    /**
     * Saves a code BufferedImage to a file in the specified format.
     * @param bufferedImage the BufferedImage to save
     * @param file          the file that the image will be saved to with appropriate extension
     */
    private static void saveBufferedImageToFile(BufferedImage bufferedImage, File file) {
        try {
            String format = getFormat(file);
            ImageIO.write(bufferedImage, format, file);
            System.out.println("Image saved successfully to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving the image.");
        }
    }

    /**
     * Extracts the file format from the file's extension.
     * @param file the file whose format needs to be extracted
     * @return the format as a string, etc .png
     */
    private static String getFormat(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "png"; // Standardformat om inget annat anges
    }

    /**
     * Converts a WritableImage to a BufferedImage.
     *
     * @param writableImage the WritableImage to convert
     * @return a BufferedImage representing the same image
     */
    private static BufferedImage convertToBufferedImage(WritableImage writableImage) {
        int width = (int) writableImage.getWidth();
        int height = (int) writableImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = writableImage.getPixelReader().getArgb(x, y);
                bufferedImage.setRGB(x, y, argb);
            }
        }

        return bufferedImage;
    }

    /**
     * Converts a Image object to a 2D array of pixel data.
     * Each pixel is represented as an integer containing RGB values.
     *
     * @param image the Image to be converted
     * @return a 2D array of integers representing the RGB values of the image's pixels
     */
    public static int[][] imageToPixelArray(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        int[][] pixelArray = new int[height][width];

        PixelReader pixelReader = image.getPixelReader();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelArray[y][x] = pixelReader.getArgb(x, y);
            }
        }
        return pixelArray;
    }

    /**
     * Converts a 2D array of pixel data to a WritableImage.
     * Each pixel is represented as an integer containing RGB-values.
     * @param pixels a 2D array of integers representing the RGB values of pixels
     * @return a WritableImage representing the image created from the pixel data
     */
    public static WritableImage pixelArrayToImage(int[][] pixels) {
        int height = pixels.length;
        int width = pixels[0].length;

        WritableImage image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelWriter.setArgb(x, y, pixels[y][x]);
            }
        }

        return image;
    }




}
