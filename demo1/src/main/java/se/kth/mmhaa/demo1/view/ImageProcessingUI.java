package se.kth.mmhaa.demo1.view;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import se.kth.mmhaa.demo1.controller.ImageController;

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

            controller.handleGrayscale(newValue.doubleValue());
        });

        sliderContainer.getChildren().add(grayscaleSlider);
    }


    public void showContrastSlider(ImageController controller) {

        sliderContainer.getChildren().clear();


        Label contrastLabel = new Label("Adjust Contrast");
        sliderContainer.getChildren().add(contrastLabel);

        Slider contrastSlider = new Slider(-1.0, 1.0, 0.0);
        contrastSlider.setShowTickMarks(true);
        contrastSlider.setShowTickLabels(true);
        contrastSlider.setMajorTickUnit(0.2);
        contrastSlider.setBlockIncrement(0.1);
        contrastSlider.setSnapToTicks(true);


        contrastSlider.valueProperty().addListener((observable, oldValue, newValue) -> {

            controller.handleContrasting(newValue.doubleValue());
        });

        sliderContainer.getChildren().add(contrastSlider);
    }
}
