package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/easybookgui1.fxml"));
			//Parent root1 = FXMLLoader.load(getClass().getResource("/application/addBookGui.fxml"));
			Scene scene = new Scene(root);
			//Scene scene1 = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene); 
			primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
