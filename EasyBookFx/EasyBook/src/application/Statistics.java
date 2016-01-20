package application;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;


public class Statistics {

	 @FXML
	    private BarChart<String, Integer> barChart;
	 @FXML
	    private CategoryAxis xAxis;
	
	 private ObservableList<String> monthNames = FXCollections.observableArrayList();
	 
	 @FXML
	    private void initialize() {
		 String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
		 monthNames.addAll(Arrays.asList(months));
		 
		 xAxis.setCategories(monthNames);
	 }
	 
	 XYChart.Series series1 = new XYChart.Series();

}
