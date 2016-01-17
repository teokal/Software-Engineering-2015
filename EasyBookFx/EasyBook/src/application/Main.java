package application;
	
import events.AddEditRoomAdminPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/loginGui.fxml"));
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
	
	public void openNewPanel(String path) {
		
		try {
			
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load( getClass().getResource(path) );
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene); 
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Room openEditRoomPanel(String path, Room room) {
		
		try {
			
			Stage primaryStage = new Stage();
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));     
			Parent root = (Parent)fxmlLoader.load();      
				
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			AddEditRoomAdminPanel panel = fxmlLoader.<AddEditRoomAdminPanel>getController();
			panel.loadRoom(room);
			
			primaryStage.setScene(scene); 
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return room;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

	
}
