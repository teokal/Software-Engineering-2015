import static org.junit.Assert.*;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

import application.Main;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class TestAdministrationLoginPanel extends GuiTest {

	static Main mainApp;
	
	@Override
	protected Parent getRootNode() {
		try {
	        if (mainApp == null) {
	            mainApp = new Main();
	            mainApp.start(stage);
	        }
	        
	        Parent root = mainApp.getParent().getScene().getRoot();
	        mainApp.getParent().getScene().setRoot(new Region());
	        return root;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Test
    public void shouldStartAppAsAdmin() {

        TextField firstname = find("#usernameUser");
        firstname.setText("admin");

        TextField lastname = find("#passUser");
        lastname.setText("admin");

        final Button search = find("#submitBtn");
        click(search);
        
        assertEquals("TypeAdmin", "admin", mainApp.typeUser);
    }

}
