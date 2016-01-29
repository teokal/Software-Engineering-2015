package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Statistics extends Application{

	@FXML
	private LineChart<String, Number> lineChart;
	
	@Override public void start(Stage stage) {
		
		
		
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Months");       
        
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Bookings of 2016");
                                
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        
        series.getData().add(new XYChart.Data<String, Number>("Jan", 23));
        series.getData().add(new XYChart.Data<String, Number>("Feb", 14));
        series.getData().add(new XYChart.Data<String, Number>("Mar", 15));
        series.getData().add(new XYChart.Data<String, Number>("Apr", 24));
        series.getData().add(new XYChart.Data<String, Number>("May", 34));
        series.getData().add(new XYChart.Data<String, Number>("Jun", 36));
        series.getData().add(new XYChart.Data<String, Number>("Jul", 22));
        series.getData().add(new XYChart.Data<String, Number>("Aug", 45));
        series.getData().add(new XYChart.Data<String, Number>("Sep", 43));
        series.getData().add(new XYChart.Data<String, Number>("Oct", 17));
        series.getData().add(new XYChart.Data<String, Number>("Nov", 29));
        series.getData().add(new XYChart.Data<String, Number>("Dec", 25));
       
        
       // Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
       // stage.setScene(scene);
       // stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
