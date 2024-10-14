package se.kth.mmhaa.demo1.view;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import se.kth.mmhaa.demo1.controller.ImageController;

import java.util.concurrent.atomic.AtomicInteger;

public class ImageProcessingUI {
    private VBox sliderContainer;

    public ImageProcessingUI() {
        sliderContainer = new VBox(10);
    }

    public VBox getSliderContainer() {
        return sliderContainer;
    }


    public void showGrayscaleSlider(ImageController controller) {
        sliderContainer.getChildren().clear();

        Label grayscaleLabel = new Label("Adjust Grayscale");
        sliderContainer.getChildren().add(grayscaleLabel);

        Slider grayscaleSlider = new Slider(0.0, 1.0, 0.0);
        grayscaleSlider.setShowTickMarks(true);
        grayscaleSlider.setShowTickLabels(true);
        grayscaleSlider.setMajorTickUnit(0.2);
        grayscaleSlider.setBlockIncrement(0.1);
        grayscaleSlider.setSnapToTicks(true);


        grayscaleSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Grayscale Strength: " + newValue.doubleValue()); // For debugging
            controller.applyGrayscale(newValue.doubleValue());
        });
        sliderContainer.getChildren().add(grayscaleSlider);
    }
    public void showContrastSlider(ImageController controller) {
        sliderContainer.getChildren().clear();

        Label contrastLabel = new Label("Adjust Contrast");
        sliderContainer.getChildren().add(contrastLabel);
        Slider contrastSlider = new Slider(0.0, 1.0, 0.0);
        contrastSlider.setShowTickMarks(true);
        contrastSlider.setShowTickLabels(true);
        contrastSlider.setMajorTickUnit(0.2);
        contrastSlider.setBlockIncrement(0.1);
        contrastSlider.setSnapToTicks(true);
        contrastSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Contrast Strength: " + newValue.doubleValue()); // For debugging
            controller.applyContrast(newValue.doubleValue());
        });
        sliderContainer.getChildren().add(contrastSlider);
    }

        public void windowLevelSlider(ImageController controller) {
            Slider levelSlider = new Slider(0.0, 255.0, 128.0);
            Slider windowSlider = new Slider(0.0, 255.0, 256.0);

            sliderContainer.getChildren().clear();
            Label windowLabel = new Label("Adjust Window");
            windowSlider.setShowTickMarks(true);
            windowSlider.setShowTickLabels(true);
            windowSlider.setMajorTickUnit(1.0);
            windowSlider.setBlockIncrement(1.0);
            windowSlider.setSnapToTicks(true);
            windowSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("Window: " + newValue.doubleValue()); //For debugging
                controller.applyWindowLevel((int)windowSlider.getValue(), (int)levelSlider.getValue());
            });

            Label levelLabel = new Label("Adjust Level");
            levelSlider.setShowTickMarks(true);
            levelSlider.setShowTickLabels(true);
            windowSlider.setMajorTickUnit(1.0);
            windowSlider.setBlockIncrement(1.0);
            levelSlider.setSnapToTicks(true);
            levelSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("Level: " + newValue.doubleValue()); //For debugging
                controller.applyWindowLevel((int)windowSlider.getValue(), (int)levelSlider.getValue());
            });
            sliderContainer.getChildren().addAll(windowLabel, windowSlider, levelLabel, levelSlider);
        }

    }



