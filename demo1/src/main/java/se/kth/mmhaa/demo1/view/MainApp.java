package se.kth.mmhaa.demo1.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import se.kth.mmhaa.demo1.controller.ImageController;
import se.kth.mmhaa.demo1.model.ImageModel;

/**
 * The MainApp class is the main point for the image-processing application, otherwise known as "entry-point".
 * Sets up main user interface
 */
public class MainApp extends Application {
    private ImageProcessingUI imageProcessingUI;


    /**
     * The main entry point for this JavaFX application. This method is called after the system is initialized.
     * @param primaryStage the primary stage for the JavaFX application, where the UI is displayed
     */
    @Override
    public void start(Stage primaryStage) {
        // Initialize models, view, and controller
        ImageModel model = new ImageModel();
        ImageModel save = new ImageModel();
        ImageDisplayView view = new ImageDisplayView();
        ImageController controller = new ImageController(model, save, view, primaryStage);
        view.setController(controller);

        imageProcessingUI = new ImageProcessingUI();

        // Create the menu bar and menus
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = createMenu("File",
                new String[]{"Open Image","Save Image", "Exit"},
                new EventHandler[]{
                        e -> controller.handleOpenImage(),
                        e-> controller.handleSaveImage(),
                        e -> primaryStage.close()}

        );

        Menu imageProcessingMenu = createMenu("Image Processing",
                new String[]{"Grayscale", "Contrast", "Window/Level"},
                new EventHandler[]{
                        e -> {
                            imageProcessingUI.showGrayscaleSlider(controller);
                            view.getRootPane().setBottom(imageProcessingUI.getSliderContainer());
                        },
                        e -> {
                            imageProcessingUI.showContrastSlider(controller);
                            view.getRootPane().setBottom(imageProcessingUI.getSliderContainer());
                        },
                        e -> {
                            imageProcessingUI.windowLevelSlider(controller);
                            view.getRootPane().setBottom(imageProcessingUI.getSliderContainer());
                        }
                }
        );

        menuBar.getMenus().addAll(fileMenu, imageProcessingMenu);

        // Set up the scene and display the stage
        Scene scene = new Scene(view.getRootPane(), 800, 600);
        primaryStage.setTitle("Image Processing by Adam and Sajad");
        primaryStage.setScene(scene);
        primaryStage.show();
        ((BorderPane) view.getRootPane()).setTop(menuBar);
    }

    /**
     * Creates a menu with the specified name and menu items, and associates event handlers with each item.
     *
     * @param menuName   the name of the menu
     * @param itemNames  an array of names for the menu items
     * @param handlers   an array of EventHandler objects - menu items
     * @return the created Menu object
     */
    private Menu createMenu(String menuName, String[] itemNames, EventHandler<ActionEvent>[] handlers) {
        Menu menu = new Menu(menuName);

        for (int i = 0; i < itemNames.length; i++) {
            MenuItem menuItem = new MenuItem(itemNames[i]);
            menuItem.setOnAction(handlers[i]);
            menu.getItems().add(menuItem);
        }

        return menu;
    }

    /**
     * The main method, which launches the app.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
