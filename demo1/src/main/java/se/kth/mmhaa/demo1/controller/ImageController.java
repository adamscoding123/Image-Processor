package se.kth.mmhaa.demo1.controller;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.kth.mmhaa.demo1.model.*;
import se.kth.mmhaa.demo1.util.FileIO;
import se.kth.mmhaa.demo1.view.ImageDisplayView;

import java.io.File;

/**
 * The ImageController class handles the interaction between the view, model, and image processing logic.
 * It provides logic to open and save images, apply different image effects (e.g., grayscale, contrast, window level),
 * and to display the histogram of an image.
 */

public class ImageController {
    private ImageModel model;
    private ImageModel save;
    private ImageDisplayView view;
    private Stage stage;

    /**
     * Constructs a new {ImageController with the specified model, save of new picture (IO), view, and stage.
     *
     * @param model the model that stores the current image data
     * @param save the model that stores the processed image data
     * @param view the view package used to display the image and histogram
     * @param stage the stage used for file dialogs (primaryStage)
     */


    public ImageController(ImageModel model, ImageModel save, ImageDisplayView view, Stage stage) {
        this.model = model;
        this.save = save;
        this.view = view;
        this.stage = stage;
    }

    /**
     * Handles saving the currently processed image to a file. Opens a file chooser to select the location for saving.
     * Only PNG format is supported (can add JPG, JPEG). If no file is selected, the save operation is canceled
     * and souts message in terminal.
     */
    public void handleSaveImage(){
        try{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save image");

            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
            File file = fileChooser.showSaveDialog(stage);

            if(file==null){
                System.out.println("No file selected");
                return;
            }
            FileIO.saveImage(file, save.getImageData());
        }catch(Exception exception){
            System.out.println("Error saving: "+exception.getMessage());
        }
    }

    /**
     * Handles opening an image file and loading it into the application. Opens a file chooser to select the image file
     * (supports PNG, JPEG, JPG, and BMP formats, but only PNG used in this case).
     * Once image is uploaded, the image is displayed and its pixel data is stored in the model using a 2D-array.
     */
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

    /**
     * Displays the histogram of the currently loaded image. If no image is loaded, a message is souted
     * and the op is cancelled.
     */
    public void showHistogram() {
        int[][] pixelData = save.getImageData();

        if (pixelData == null) {
            System.out.println("No image loaded. Please load an image first.");
            return;
        }
        int[][] histogramData = HistogramCalc.calculateHistogram(pixelData);
        view.showHistogramChart(histogramData);
    }

    /**
     * Applies the specified image processing effect using IProcessor.
     * Updates the view to display the processed image.
     * @param processor the image processor to apply to the image, updating display
     */
    public void applyEffect(IProcessor processor) {
        int[][] originalImg = model.getImageData();
        int[][] processedImg = processor.processImage(originalImg);
        save.setImageData(processedImg);
        view.updateImageDisplay(FileIO.pixelArrayToImage(processedImg));
    }

    /**
     * Applies a grayscale filter to the image with the specified grayscale strength.
     *
     * @param greyFactor the strength of the grayscale effect to apply
     */
    public void applyGrayscale(double greyFactor) {
        var processor = new GreyscaleProcessor();
        processor.setStrength(greyFactor);
        applyEffect(processor);
    }

    /**
     * Applies a contrast filter to the image with the specified contrast factor.
     *
     * @param contrastFactor the strength of the contrast effect to apply
     */
    public void applyContrast(double contrastFactor) {
        var processor = new ContrastProcessor();
        processor.setStrength(contrastFactor);
        applyEffect(processor);
    }

    /**
     * Applies window leveling to the image using the specified window and level factors.
     *
     * @param windowF the window factor to apply
     * @param levelF the level factor to apply
     */
    public void applyWindowLevel(int windowF, int levelF){
        var processor = new WindowLevelProcessor();
        processor.setWindow(windowF);
        processor.setLevel(levelF);
        applyEffect(processor);
    }
}
