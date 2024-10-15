module se.kth.mmhaa.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires java.sql;
    //requires javafx.swing;

    opens se.kth.mmhaa.demo1;
    exports se.kth.mmhaa.demo1.view to javafx.graphics;
    exports se.kth.mmhaa.demo1.controller to javafx.graphics;
}