package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addBookGui.fxml"));
			NewBookController controller = new NewBookController(primaryStage);
			fxmlLoader.setController(controller);
			
			Scene scene = new Scene( (Parent)fxmlLoader.load() );
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Imaginary Hotel - Enjoy your staying in our paradise!");
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UTILITY);

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