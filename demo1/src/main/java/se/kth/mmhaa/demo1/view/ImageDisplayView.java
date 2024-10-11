package se.kth.mmhaa.demo1.view;


import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class ImageView extends BorderPane {
    private ImageView imageView;

    public ImageView() {
        imageView = new ImageView();
        this.setCenter(imageView);
    }

    public void updateImageDisplay(Image image) {
        imageView.setImage(image);
    }
}
