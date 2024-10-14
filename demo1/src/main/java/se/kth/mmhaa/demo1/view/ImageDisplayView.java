package se.kth.mmhaa.demo1.view;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import se.kth.mmhaa.demo1.controller.ImageController;

public class ImageDisplayView {
    private ImageView imageView;
    private TabPane tabPane;
    private BorderPane imagePane;
    private BorderPane histogramPane;
    private ImageController controller;
    private BorderPane rootPane;

    public ImageDisplayView() {
        rootPane = new BorderPane();

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imagePane = new BorderPane();
        imagePane.setCenter(imageView);

        histogramPane = new BorderPane();

        tabPane = new TabPane();

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

    public void setController(ImageController controller) {
        this.controller = controller;
    }

    public BorderPane getRootPane() {
        return rootPane;
    }

    public void updateImageDisplay(Image image) {
        if (image != null) {
            imageView.setImage(image);
            imageView.setFitWidth(800);
            imageView.setFitHeight(600);
            System.out.println("Image displayed successfully.");
        }
    }

    public ImageView getImageView(){
        return imageView;
    }

    // Show the histogram
    public void showHistogramChart(int[][] histogramData) {
        if (histogramData == null || histogramData.length != 4 || histogramData[0].length != 256) {
            System.out.println("Invalid histogram data.");
            return;
        }
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Intensity");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Count");

        BarChart<String, Number> histogramChart = new BarChart<>(xAxis, yAxis);
        histogramChart.setTitle("Image Histogram");

        XYChart.Series<String, Number> alphaSeries = new XYChart.Series<>();
        alphaSeries.setName("Alpha");

        XYChart.Series<String, Number> redSeries = new XYChart.Series<>();
        redSeries.setName("Red");

        XYChart.Series<String, Number> greenSeries = new XYChart.Series<>();
        greenSeries.setName("Green");

        XYChart.Series<String, Number> blueSeries = new XYChart.Series<>();
        blueSeries.setName("Blue");


        for (int i=0; i<64; i+=4 ) {
            alphaSeries.getData().add(new XYChart.Data<>(String.valueOf(i), histogramData[0][i]));
            redSeries.getData().add(new XYChart.Data<>(String.valueOf(i), histogramData[1][i]));
            greenSeries.getData().add(new XYChart.Data<>(String.valueOf(i), histogramData[2][i]));
            blueSeries.getData().add(new XYChart.Data<>(String.valueOf(i), histogramData[3][i]));
        }

        histogramChart.getData().addAll(alphaSeries, redSeries, greenSeries, blueSeries);
        histogramPane.setCenter(histogramChart); // Correct place to show the histogram
        System.out.println("Histogram displayed successfully.");
    }
}
