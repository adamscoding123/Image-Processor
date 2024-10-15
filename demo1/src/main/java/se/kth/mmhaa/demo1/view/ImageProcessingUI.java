package se.kth.mmhaa.demo1.view;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import se.kth.mmhaa.demo1.controller.ImageController;

/**
 * The ImageProcessingUI class provides a user interface for adjusting various image processing effects.
 * It allows for interaction with sliders to modify grayscale, contrast, and window-level.
 */
public class ImageProcessingUI {
    private VBox sliderContainer;
    private double windowValue = 256.0;
    private double levelValue = 128.0;

    /**
     * Constructs a new ImageProcessingUI, initializing the slider container.
     */
    public ImageProcessingUI() {
        sliderContainer = new VBox(10);
    }

    /**
     * Returns the container that holds sliders
     * @return the VBox that contains the sliders
     */
    public VBox getSliderContainer() {
        return sliderContainer;
    }


    /**
     * Functional interface used to apply actions when the slider values change.
     * Compacts code
     */
    @FunctionalInterface
    interface SliderAction {
        /**
         * Applies the specified action dependent on the slider value and controller.
         * @param value      the value from the slider
         * @param controller the ImageController used to apply the effect
         */
        void apply(double value, ImageController controller);
    }

    /**
     * Creates and displays a slider for adjusting image processing settings. The slider's properties and behavior
     * are defined by the arguments passed
     *
     * @param controller      the ImageController used to apply the effect
     * @param name            the name of the effect (displayed as a label)
     * @param min             the minimum value for the slider
     * @param max             the maximum value for the slider
     * @param init            the initial value of the slider
     * @param majorTick       the major tick unit for the slider
     * @param blockIncrement  the block increment for the slider
     * @param showTickMarks   whether or not to show tick marks on the slider (boolean, true or false)
     * @param showTickLabels  whether or not to show tick labels on the slider  (boolean, true or false)
     * @param snapToTicks     whether or not the slider should snap to tick values  (boolean, true or false)
     * @param action          the action to be applied when the slider value changes (SliderAction callback used)
     */
    private void createAndShowSlider(ImageController controller, String name, double min, double max, double init, double majorTick,
                                     double blockIncrement, boolean showTickMarks, boolean showTickLabels,
                                     boolean snapToTicks, SliderAction action) {

        /*sliderContainer.getChildren().clear(); ISTÄLLET för att cleara här så clearar vi i varje enskild
            slidermetod - tillåter kompatibilitet för att visa mer än 1 slider per menuitem*/

        // Label for the slider
        Label nameLabel = new Label(name);
        sliderContainer.getChildren().add(nameLabel);

        // Create the slider with the specified properties
        Slider slider = new Slider(min, max, init);
        slider.setMajorTickUnit(majorTick);
        slider.setBlockIncrement(blockIncrement);
        slider.setShowTickMarks(showTickMarks);
        slider.setShowTickLabels(showTickLabels);
        slider.setSnapToTicks(snapToTicks);

        // Add listener to apply action when slider value changes
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(name + " Strength: " + newValue.doubleValue());
            action.apply(newValue.doubleValue(), controller);
        });

        // Add the slider to the container
        sliderContainer.getChildren().add(slider);
    }



    /**
     * Displays a slider for adjusting the contrast effect on the image.
     * The contrast strength ranges from minimum 0.0 to maximum 1.0, but initiates at 0.0.
     * @param controller the ImageController that will apply the contrast effect (in this case applyGrayscale)
     */
    public void showGrayscaleSlider(ImageController controller) {
        sliderContainer.getChildren().clear();
        createAndShowSlider(controller, "Adjust Grayscale", 0.0, 1.0, 0.0, 0.2,
                                    0.1, true, true, true,
                (value, controllerAction) -> controllerAction.applyGrayscale(value));
    }

    /**
     * Displays a slider for adjusting the contrast effect on the image.
     * The contrast strength ranges from minimum 0.0 to maximum 1.0, but initiates at 0.0.
     * @param controller the ImageController that will apply the contrast effect (in this case applyContrast)
     */
    public void showContrastSlider(ImageController controller) {
        sliderContainer.getChildren().clear();
        createAndShowSlider(controller, "Adjust Contrast", 0.0, 1.0, 0.0, 0.2,
                                    0.1, true, true, true,
                                    (value, controllerAction) -> controllerAction.applyContrast(value));
    }

    /**
     * Displays two sliders for adjusting the window and level values used for window-level processing.
     * The sliders allow adjusting the window and level independent of each-other.
     * @param controller the ImageController that will apply the window-level effect
     */
    public void windowLevelSlider(ImageController controller) {
        sliderContainer.getChildren().clear();

        createAndShowSlider(controller, "Adjust Window", 0.0, 255.0, 128.0, 1.0,
                                        1.0, true, true, true,
                                        (value, controllerAction) ->{
                                        windowValue=value; controllerAction.applyWindowLevel((int)windowValue, (int)levelValue);
                                        });

        createAndShowSlider(controller, "Adjust Level", 0.0, 255.0, 128.0, 1.0,
                                    1.0, true, true, true,
                                        (value, controllerAction) ->{
                                            levelValue=value; controllerAction.applyWindowLevel((int)windowValue, (int)levelValue);
                                        });
    }
}
