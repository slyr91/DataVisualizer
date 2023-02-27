module com.example.datavisualizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.datavisualizer to javafx.fxml;
    exports com.example.datavisualizer;
}