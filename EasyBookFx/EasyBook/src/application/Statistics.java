package application;

import javafx.application.Application;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;

import database.Conn;

public class Statistics extends Application{
	
	public LineChart<String, Number> showStatsForMonthsOfYear(LineChart<String, Number> linechart,
			int from, int until, int year, boolean income){
		
		// Each month is been represented from i-1. So months[0] is for January, months[11] for December
		int[] months = new int[12];
    	int max = 0;
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName("Months");
        
        try {
        	Connection conn = Conn.connect();
        	
        	String query;
        	if (!income) {
        		query = "SELECT MONTH(`check_in`) MONTH, COUNT(*) COUNT ";
        	} else {
        		query = "SELECT MONTH(`check_in`) MONTH, SUM(`money_received`) COUNT ";
        	}
        	query += "FROM `bookings` "
        			+ "WHERE YEAR(`check_in`)=? "
        			+ "GROUP BY MONTH(`check_in`)";

        	PreparedStatement ps = conn.prepareStatement(query);
        	ps.setInt(1, year);

        	ResultSet rs = ps.executeQuery();
        	
        	while ( rs.next() ) {
        		months[rs.getInt("MONTH")-1] = rs.getInt("COUNT");
        	}
        	
        	for(int i=from; i<until+1; i++){
        		if ( max < months[i] ){ max = months[i]; }
        		series.getData().add(new XYChart.Data<String, Number>(
        				(new DateFormatSymbols().getMonths()[i]), months[i] ));
        	}

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        linechart.getData().clear();
        linechart.getData().add(series);
		
		return linechart;
	}
 
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage arg0) throws Exception {
		
	}
}
