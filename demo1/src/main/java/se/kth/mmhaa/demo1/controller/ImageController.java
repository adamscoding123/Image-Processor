package se.kth.mmhaa.demo1.controller;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import se.kth.mmhaa.demo1.model.ImageModel;
import se.kth.mmhaa.demo1.view.ImageDisplayView;

import java.io.File;

public class ImageController {
    private ImageModel model;
    private ImageDisplayView view;

    public ImageController(ImageModel model, ImageDisplayView view) {
        this.model = model;
        this.view = view;
    }

    public void handleOpenImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            // Omvandla filen till en JavaFX Image-objekt och visa den
            Image image = new Image(file.toURI().toString());
            view.updateImageDisplay(image);

            // Här kan du omvandla bilden till en pixelmatris och lagra i modellen
            // model.setImageData(...);
        }
    }
}
