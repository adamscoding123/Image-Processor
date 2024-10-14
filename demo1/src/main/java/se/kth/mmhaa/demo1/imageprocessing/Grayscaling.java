package se.kth.mmhaa.demo1.imageprocessing;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;

public class Grayscaling {

    public void applyGrayscaleEffect(ImageView imageView, double factor) {
        if (imageView == null || imageView.getImage() == null) {
            System.out.println("No image uploaded");
            return;
        }
        // Create a ColorAdjust effect
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1 * factor);

        imageView.setEffect(colorAdjust);
        System.out.println("Image grayscaled with factor: " + factor);
    }
}
