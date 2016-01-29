package application;

import javafx.application.Application;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Conn;
import javafx.beans.property.SimpleStringProperty;

public class Statistics extends Application{
	
	try {
		
		Connection conn = Conn.connect();
		String query = "SELECT MONTH(`check_in`) MONTH, COUNT(*) COUNT FROM `bookings` WHERE YEAR(`check_in`)=? GROUP BY MONTH(`check_in`)";
		int year = 2016;
		int yearNow, count;
		PreparedStatement ps = conn.prepareStatement(query);

		ps.setInt(1, year);
		
		ResultSet rs = ps.executeQuery();
		
		while ( rs.next() ) {
			yearNow = rs.getInt("MONTH");
			count = rs.getInt("COUNT");
		}
		conn.close();

	} catch (IOException|SQLException e) {
		e.printStackTrace();
	}
	
	
	

	public LineChart<String, Number> show( LineChart<String, Number> linechart) {

        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Months");       
                        
        linechart.setTitle("Bookings of 2016");
                                
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
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
       
        linechart.getData().add(series);
        
        return linechart;
	}
 
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
