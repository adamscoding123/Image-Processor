package se.kth.mmhaa.demo1.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIO {

    // Läs en bildfil och returnera den som ett Image-objekt
    public static Image readImage(File file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return new Image(inputStream);
        }
    }

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

    // Konvertera en pixelmatris till ett WritableImage-objekt
    public static WritableImage pixelArrayToImage(int[][] pixels) {
        int height = pixels.length;
        int width = pixels[0].length;

        WritableImage image = new WritableImage(width, height);
        javafx.scene.image.PixelWriter pixelWriter = image.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelWriter.setArgb(x, y, pixels[y][x]);
            }
        }

        return image;
    }
}
