package application;
	
import events.AddEditRoomAdminPanel;
import events.AdministrationLogin;
import events.AdministrationPanel;
import events.ChangeBookPanel;
import events.NewBookController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	
	private Parent parent;
	private Stage primaryStage;
	public Scene guiScene;
	public String typeUser;
	
	@Override
	public void start(Stage primaryStage) {
		openLoginPanel(primaryStage);
	}
	
	public Parent getParent() {
		return parent;
	}
	
	public void openLoginPanel(Stage primaryStage) {
		
		try {
//			Stage primaryStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/loginGui.fxml"));
			AdministrationLogin loginPanel = new AdministrationLogin(this);
			
			fxmlLoader.setController( loginPanel );
			parent = fxmlLoader.load();
			
			Scene scene = new Scene( parent );
			scene.getStylesheets().add( getClass().getResource("application.css").toExternalForm() );
			
			primaryStage.setTitle("Login to EasyBook - Integrated Booking System");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene); 
			primaryStage.show();
			
			typeUser = loginPanel.userType;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void openNewPanel(String path, String title) {
		
		try {
			
//			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load( getClass().getResource(path) );
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle(title);
			primaryStage.setResizable(false);
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
			primaryStage.setResizable(false);
			primaryStage.setScene(scene); 
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Room openEditRoomPanel(String path, String title, Room room, AdministrationPanel o, boolean newRoom) {
		
		try {
			Stage primaryStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));     
			
			Parent root = (Parent)fxmlLoader.load();      
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			AddEditRoomAdminPanel panel = fxmlLoader.<AddEditRoomAdminPanel>getController();
			if (!newRoom) {
				panel.loadRoom(room, o);
			} else {
				panel.setParent(o);
			}

			primaryStage.setTitle(title);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene); 
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		              o.showAllRooms(null);
		          }
		      });        

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return room;
	}
	
	public void openBookPanel( boolean newBook ) {
	       
        try {
   
            Stage primaryStage = new Stage();
            primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/addBookGui.fxml"));
            NewBookController controller = new NewBookController(primaryStage);
            fxmlLoader.setController(controller);
           
            Scene scene = new Scene( (Parent)fxmlLoader.load() );
           
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Imaginary Hotel - Enjoy your staying in our paradise!");
            primaryStage.setResizable(false);
 
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
