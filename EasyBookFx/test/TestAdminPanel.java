import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;

import com.google.common.util.concurrent.SettableFuture;

import application.Main;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TestAdminPanel extends GuiTest {
	
	Main app;

    private static final SettableFuture<Stage> stageFuture = SettableFuture.create();
	
    public static class MyTestFxApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            TestAdminPanel.stageFuture.set(primaryStage);
        }
    }
    
    @Test
    public void test(){
    	assertEquals("admin",app.typeUser);
    }
	
    @Override
    protected Parent getRootNode() {
        try {
            if (app == null) {
            	app = new Main();
            	app.openEasyBookGUIAdminPanel("admin");
            }

            Parent root = app.getParent().getScene().getRoot();
            app.getParent().getScene().setRoot(new Region());
            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Parent root = app.getParent().getScene().getRoot();
        return root;
    }
	
    @Before
    @Override
    public void setupStage() throws Throwable {
    	getRootNode();
    }


}
