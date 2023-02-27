package com.example.datavisualizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WelcomeScreen {

    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    final FileChooser fileChooser = new FileChooser();

    @FXML
    private TextField xAxisTitle;

    @FXML
    private TextField yAxisTitle;

    @FXML
    private ToggleGroup Style;

    @FXML
    private TextField fileField;

    @FXML
    void selectFile(ActionEvent event) {
        File file = fileChooser.showOpenDialog(stage);

        if(file != null) {
            fileField.setText(file.getAbsolutePath());
        }
    }

    @FXML
    void generateGraph(ActionEvent event) {
        List<XYChart.Series<Number, Number>> data = new ArrayList<>();

        File file = new File(fileField.getText());

        CSVFile csv = new CSVFile(file);
        csv.ingestFile();

        for(int i = 0; i < csv.rowCount; i++) {
            XYChart.Series<Number, Number> d = new XYChart.Series<>();

            String[] row = csv.getRow(i).split(",");

            d.setName(row[0]);

            for(int j = 0; j < csv.columnCount; j++) {
                d.getData().add(new XYChart.Data<>(Double.parseDouble(csv.getColumn(j)), Double.parseDouble(row[j + 1])));
            }

            data.add(d);
        }

        Scene view;

        if(((RadioButton) Style.getSelectedToggle()).getText().equals("Bar")) {

            NumberAxis xAxis = new NumberAxis(1986, 1995, 1);
            NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel(xAxisTitle.getText());
            yAxis.setLabel(yAxisTitle.getText());

            BarChart<Number, Number> barChart = new BarChart<>(xAxis, yAxis);

            for (XYChart.Series<Number, Number> d :
                    data) {
                barChart.getData().add(d);
            }


            view = new Scene(barChart, 840,680);

        } else {
            NumberAxis xAxis = new NumberAxis(1986, 1995, 1);
            NumberAxis yAxis = new NumberAxis();

            xAxis.setLabel(xAxisTitle.getText());
            yAxis.setLabel(yAxisTitle.getText());

            LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

            for (XYChart.Series<Number, Number> d :
                    data) {
                lineChart.getData().add(d);
            }


            view = new Scene(lineChart, 840,680);
        }

        stage.setScene(view);
        stage.show();
    }


}
