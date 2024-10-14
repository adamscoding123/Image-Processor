package se.kth.mmhaa.demo1.controller;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.kth.mmhaa.demo1.model.HistogramCalc;
import se.kth.mmhaa.demo1.util.FileIO;
import se.kth.mmhaa.demo1.model.ImageModel;
import se.kth.mmhaa.demo1.view.ImageDisplayView;
import se.kth.mmhaa.demo1.model.ImageProcessor;
import se.kth.mmhaa.demo1.model.IProcessor;

import java.io.File;

public class ImageController {
    private ImageModel model;
    private ImageDisplayView view;
    private Stage stage;

    private int[][] originalImageData;

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

            originalImageData = FileIO.imageToPixelArray(image);
            model.setImageData(FileIO.imageToPixelArray(image));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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
    public void applyGrayscale(double greyFactor) {
        IProcessor processor = new ImageProcessor();
        int[][] originalImg = model.getImageData();
        int[][] processedImg = ((ImageProcessor) processor).greyscaleProcessor(originalImageData, greyFactor);
        model.setImageData(processedImg);
        view.updateImageDisplay(FileIO.pixelArrayToImage(processedImg));
    }

    public void applyContrast(double contrastFactor) {
        IProcessor processor = new ImageProcessor();
        int[][] originalImg = model.getImageData();
        int[][] processedImg = ((ImageProcessor) processor).contrastProcessor(originalImageData, contrastFactor);
        model.setImageData(processedImg);
        view.updateImageDisplay(FileIO.pixelArrayToImage(processedImg));
    }


}
