package application;
	
import events.AddEditRoomAdminPanel;
import events.AdministrationPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	public String userTypeInit;
	
	@Override
	public void start(Stage primaryStage) {
		openNewPanel( "/application/loginGui.fxml", "EasyBook" );
	}
	
	public void openNewPanel(String path, String title) {
		
		try {
			
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load( getClass().getResource(path) );
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle(title);
			primaryStage.setScene(scene); 
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openEasyBookGUIAdminPanel( String userType ) {
		
		try {
    
			Stage primaryStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/easybookgui1.fxml"));
			AdministrationPanel adminpanel = new AdministrationPanel( userType );
			
			fxmlLoader.setController( adminpanel );
			Scene scene = new Scene( (Parent)fxmlLoader.load() );
			scene.getStylesheets().add( getClass().getResource("application.css").toExternalForm() );
			
			primaryStage.setTitle("EasyBook - Integrated Booking System");
			primaryStage.setScene(scene); 
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Room openEditRoomPanel(String path, String title, Room room) {
		
		try {
			Stage primaryStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));     
			
			Parent root = (Parent)fxmlLoader.load();      
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			AddEditRoomAdminPanel panel = fxmlLoader.<AddEditRoomAdminPanel>getController();
			panel.loadRoom(room);
			
			primaryStage.setTitle(title);
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
