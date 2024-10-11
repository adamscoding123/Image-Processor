package se.kth.mmhaa.demo1.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ImageDisplayView extends BorderPane {
    private ImageView imageView;

    public ImageDisplayView() {
        imageView = new ImageView();
        this.setCenter(imageView);
    }

    public void updateImageDisplay(Image image) {
        imageView.setImage(image);
    }
}
