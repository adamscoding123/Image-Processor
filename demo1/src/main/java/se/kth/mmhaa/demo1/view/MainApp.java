package se.kth.mmhaa.demo1.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.kth.mmhaa.demo1.model.ImageModel;
import se.kth.mmhaa.demo1.controller.ImageController;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        ImageModel model = new ImageModel();
        ImageDisplayView view = new ImageDisplayView();
        ImageController controller = new ImageController(model, view);

        Scene scene = new Scene(view, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Image Processing App");

        primaryStage.show();

        // Låt användaren öppna en bild
        controller.handleOpenImage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}