package se.kth.mmhaa.demo1.controller;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.kth.mmhaa.demo1.model.HistogramCalc;
import se.kth.mmhaa.demo1.model.ImageModel;
import se.kth.mmhaa.demo1.view.ImageDisplayView;
import se.kth.mmhaa.demo1.imageprocessing.Grayscaling;
import se.kth.mmhaa.demo1.imageprocessing.Contrasting;

import java.io.File;

public class ImageController {
    private ImageModel model;
    private ImageDisplayView view;
    private Stage stage;

    public ImageController(ImageModel model, ImageDisplayView view, Stage stage) {
        this.model = model;
        this.view = view;
        this.stage = stage;
    }

    // Hantera öppningen av en bild
    public void handleOpenImage() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");

            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp"));

            File file = fileChooser.showOpenDialog(stage);

            if (file == null) {
                System.out.println("No file selected.");
                return;
            }

            System.out.println("Selected file: " + file.getAbsolutePath());

            // Ladda bilden
            Image image = new Image(file.toURI().toString());

            if (image.isError()) {
                System.out.println("Error loading image: " + image.getException());
                return;
            }

            System.out.println("Image loaded successfully!");

            view.updateImageDisplay(image);

            model.setImageData(getImagePixelData(image));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hämta pixeldata från bilden
    private int[][] getImagePixelData(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        int[][] pixelData = new int[height][width];

        PixelReader pixelReader = image.getPixelReader();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelData[y][x] = pixelReader.getArgb(x, y); // argb value som är alpha, red, green, blue
            } // färg i digital grafik, image processing
        }
        return pixelData;
    }

    // Visa histogrammet
    public void showHistogram() {
        int[][] pixelData = model.getImageData();

        if (pixelData == null) {
            System.out.println("No image loaded. Please load an image first.");
            return;
        }
        int[][] histogramData = HistogramCalc.calculateHistogram(pixelData);
        // Debugging: Print histogram data
        // System.out.println("Histogram Data:");
        // for (int i = 0; i < histogramData.length; i++) {
        // System.out.print("Channel " + i + ": ");
        // for (int j = 0; j < histogramData[i].length; j++) {
        // System.out.print(histogramData[i][j] + " ");
        // }
        // System.out.println();
        // }

        view.showHistogramChart(histogramData);
    }

    public void handleGrayscale(double grayscaleFactor) {
        Grayscaling grayscaling = new Grayscaling();
        grayscaling.applyGrayscaleEffect(view.getImageView(), grayscaleFactor);
    }

    public void handleContrasting(double contrastFactor) {
        Contrasting contrasting = new Contrasting(contrastFactor);
        contrasting.applyContrastEffect(view.getImageView());

    }
}
