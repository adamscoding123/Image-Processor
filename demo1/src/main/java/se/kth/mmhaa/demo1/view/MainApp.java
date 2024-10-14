package se.kth.mmhaa.demo1.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import se.kth.mmhaa.demo1.controller.ImageController;
import se.kth.mmhaa.demo1.model.ImageModel;

public class MainApp extends Application {
    private ImageProcessingUI imageProcessingUI;

    @Override
    public void start(Stage primaryStage) {
        ImageModel model = new ImageModel();
        ImageDisplayView view = new ImageDisplayView();
        ImageController controller = new ImageController(model, view, primaryStage);
        view.setController(controller);

        imageProcessingUI = new ImageProcessingUI();

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem openImageItem = new MenuItem("Open Image");
        openImageItem.setOnAction(e -> controller.handleOpenImage());
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());
        fileMenu.getItems().addAll(openImageItem, exitItem);

        Menu imageProcessingMenu = new Menu("Image Processing");
        MenuItem grayscaleItem = new MenuItem("Grayscale");
        grayscaleItem.setOnAction(e -> {
            imageProcessingUI.showGrayscaleSlider(controller); // Process and show grayscale slider
            view.getRootPane().setBottom(imageProcessingUI.getSliderContainer()); // add slider container to rootpane view (bottom)
        });

        MenuItem contrastItem = new MenuItem("Contrast");
        contrastItem.setOnAction(e->{
            imageProcessingUI.showContrastSlider(controller);
            view.getRootPane().setBottom(imageProcessingUI.getSliderContainer());
        });

        imageProcessingMenu.getItems().addAll(grayscaleItem, contrastItem);

        menuBar.getMenus().addAll(fileMenu, imageProcessingMenu);

        Scene scene = new Scene(view.getRootPane(), 800, 600);
        primaryStage.setTitle("Image Processing App with Tabs");
        primaryStage.setScene(scene);
        primaryStage.show();

        ((BorderPane) view.getRootPane()).setTop(menuBar);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
