package se.kth.mmhaa.demo1.controller;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.kth.mmhaa.demo1.model.*;
import se.kth.mmhaa.demo1.util.FileIO;
import se.kth.mmhaa.demo1.view.ImageDisplayView;

import java.io.File;


public class ImageController {
    private ImageModel model;
    private ImageModel save;
    private ImageDisplayView view;
    private Stage stage;


    public ImageController(ImageModel model, ImageModel save, ImageDisplayView view, Stage stage) {
        this.model = model;
        this.save = save;
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
            Image image = FileIO.readImage(file);
            if (image.isError()) {
                System.out.println("Error loading image: " + image.getException());
                return;
            }
            System.out.println("Image loaded successfully!");

            view.updateImageDisplay(image);
            var imgData = FileIO.imageToPixelArray(image);
            model.setImageData(imgData);
            save.setImageData(imgData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showHistogram() {
        int[][] pixelData = save.getImageData();

        if (pixelData == null) {
            System.out.println("No image loaded. Please load an image first.");
            return;
        }
        int[][] histogramData = HistogramCalc.calculateHistogram(pixelData);
        view.showHistogramChart(histogramData);
    }

    public void applyEffect(IProcessor processor) {
        int[][] originalImg = model.getImageData();
        int[][] processedImg = processor.processImage(originalImg);
        save.setImageData(processedImg);
        view.updateImageDisplay(FileIO.pixelArrayToImage(processedImg));
    }

    public void applyGrayscale(double greyFactor) {
        var processor = new GreyscaleProcessor();
        processor.setStrength(greyFactor);
        applyEffect(processor);
    }

    public void applyContrast(double contrastFactor) {
        var processor = new ContrastProcessor();
        processor.setStrength(contrastFactor);
        applyEffect(processor);
    }

    public void applyWindowLevel(int windowF, int levelF){
        var processor = new WindowLevelProcessor();
        processor.setWindow(windowF);
        processor.setLevel(levelF);
        applyEffect(processor);
    }
}
