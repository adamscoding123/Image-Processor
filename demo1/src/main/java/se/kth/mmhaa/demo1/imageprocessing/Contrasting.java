package se.kth.mmhaa.demo1.imageprocessing;

import javafx.scene.image.ImageView;
import javafx.scene.effect.ColorAdjust;

public class Contrasting {

    private final double contrastFactor;

    public Contrasting(double contrastFactor) {
        this.contrastFactor = contrastFactor;
    }

    public void applyContrastEffect(ImageView imageView) {
        if (imageView.getImage() == null) {
            System.out.println("No image loaded");
            return;
        }

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(contrastFactor);

        imageView.setEffect(colorAdjust);
        System.out.println("Contrast effect applied, contrastfacotr :  " + contrastFactor);
    }

}
