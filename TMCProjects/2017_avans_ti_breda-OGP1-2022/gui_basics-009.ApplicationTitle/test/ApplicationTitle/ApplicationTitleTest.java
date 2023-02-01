package ApplicationTitle;

import fi.helsinki.cs.tmc.edutestutils.MockStdio;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.testfx.framework.junit.ApplicationTest;

@Points("gui_basics-9")
public class ApplicationTitleTest {

    @Test
    public void noTests() {

    }

}

/*
@Points("12-09")
public class ApplicationTitleTest extends ApplicationTest {

    private Stage stage;
    
    @Rule
    public MockStdio io = new MockStdio();


    static {
        if (Boolean.getBoolean("SERVER")) {

            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("glass.platform", "Monocle");
            System.setProperty("monocle.platform", "Headless");
            System.setProperty("java.awt.headless", "false");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Username sovellus = new Username();

        try {
            Application app = Application.class.cast(sovellus);
        } catch (Throwable t) {
            fail("MyApplication doesn't implement the Application interface.");
        }

        try {
            Reflex.reflect(Username.class).method("start").returningVoid().taking(Stage.class).invokeOn(sovellus, stage);
        } catch (Throwable ex) {
            fail("Cannot use methode start. Error:" + ex.getMessage());
        }

        this.stage = stage;
    }
    
    @Test
    public void AppTitle1() {
        CheckingApplicationTitle("Avans");
    }
    
    @Test
    public void AppTitle2() {
        CheckingApplicationTitle("Avans Technische Informatica");
    }
    
    
    public void CheckingApplicationTitle(String appTitle) {
        
        
       // TODO implement parameter on startup
        ReflectionUtils.newInstanceOfClass(Main.class);
        io.setSysIn(appTitle + "\n");
        Main.main(new String[0]);
        assertEquals("Application title is not set correctly, expected: ", appTitle, this.stage.getTitle());
        
        
       
    }
}*/
