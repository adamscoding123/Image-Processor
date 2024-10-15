package se.kth.mmhaa.demo1.view;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import se.kth.mmhaa.demo1.controller.ImageController;

/**
 * The ImageDisplayView class represents the view component - MVC (Model-View-Controller)
 * It is responsible for displaying the image and histogram, and it interacts with the ImageController.
 */
public class ImageDisplayView {
    private ImageView imageView;
    private TabPane tabPane;
    private BorderPane imagePane;
    private BorderPane histogramPane;
    private ImageController controller;
    private BorderPane rootPane;

    /**
     * Constructs a new ImageDisplayView that sets up the display of the image and histogram.
     * The view is divided into two tabs: one for displaying the image and another for the histogram.
     */
    public ImageDisplayView() {
        rootPane = new BorderPane();

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imagePane = new BorderPane();
        imagePane.setCenter(imageView);

        tabPane = new TabPane();
        histogramPane = new BorderPane();

        Tab imageTab = new Tab("Show Image");
        imageTab.setContent(imagePane);
        imageTab.setClosable(false);

        Tab histogramTab = new Tab("Show Histogram");
        histogramTab.setContent(histogramPane);
        histogramTab.setClosable(false);

        tabPane.getTabs().addAll(imageTab, histogramTab);
        rootPane.setCenter(tabPane);

        histogramTab.setOnSelectionChanged(event -> {
            if (histogramTab.isSelected()) {
                controller.showHistogram();
            }
        });
    }

    /**
     * Sets the ImageController for this view, allowing it to communicate with the controller.
     * @param controller associated with this view
     */
    public void setController(ImageController controller) {
        this.controller = controller;
    }

    /**
     * Returns the root pane of this view, which contains the layout for displaying the image and histogram.
     * @return the root BorderPane of the view
     */
    public BorderPane getRootPane() {
        return rootPane;
    }

    /**
     * Updates the image displayed in the view.
     * @param image the Image object to display
     */
    public void updateImageDisplay(Image image) {
        if (image != null) {
            imageView.setImage(image);
            imageView.setFitWidth(800);
            imageView.setFitHeight(600);
            System.out.println("Image displayed successfully.");
        }
    }

    /**
     * Returns the ImageView component that is used to display the image.
     * @return the ImageView used to display the image
     */
    public ImageView getImageView(){
        return imageView;
    }

    /**
     * Displays the histogram chart based on histogram data
     * The histogram shows the distribution of pixel intensities for the color channels
     * @param histogramData a 2D array containing the histogram data, where each row is a color channel
     *                      and each column represents intensity values
     */
    // Show the histogram
    public void showHistogramChart(int[][] histogramData) {
        if (histogramData == null || histogramData.length != 3 || histogramData[0].length != 256) {
            System.out.println("Invalid histogram data.");
            return;
        }
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Intensity");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Count");

        // BarChart<String, Number> histogramChart = new BarChart<>(xAxis, yAxis);
        LineChart<String, Number> histogramChart = new LineChart<>(xAxis, yAxis);
        histogramChart.setCreateSymbols(false);
        histogramChart.setTitle("Image Histogram");

        XYChart.Series<String, Number> alphaSeries = new XYChart.Series<>();
        alphaSeries.setName("Alpha");

        XYChart.Series<String, Number> redSeries = new XYChart.Series<>();
        redSeries.setName("Red");

        XYChart.Series<String, Number> greenSeries = new XYChart.Series<>();
        greenSeries.setName("Green");

        XYChart.Series<String, Number> blueSeries = new XYChart.Series<>();
        blueSeries.setName("Blue");

        for (int i = 0; i < 256; i += 4) {
            redSeries.getData().add(new XYChart.Data<>(String.valueOf(i), histogramData[0][i]));
            greenSeries.getData().add(new XYChart.Data<>(String.valueOf(i), histogramData[1][i]));
            blueSeries.getData().add(new XYChart.Data<>(String.valueOf(i), histogramData[2][i]));
        }

        histogramChart.getData().addAll(redSeries, greenSeries, blueSeries);
        histogramPane.setCenter(histogramChart); // Correct place to show the histogram
        System.out.println("Histogram displayed successfully.");
    }
}
