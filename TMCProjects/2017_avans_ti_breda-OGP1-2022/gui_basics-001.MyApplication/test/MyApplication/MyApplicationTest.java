package MyApplication;

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.testfx.framework.junit.ApplicationTest;

public class MyApplicationTest extends ApplicationTest {

    private Stage stage;

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
        MyApplication myApplication = new MyApplication();

        try {
            Application app = Application.class.cast(myApplication);
        } catch (Throwable t) {
            fail("MyApplication doesn't implement the Application interface.");
        }

        try {
            Reflex.reflect(MyApplication.class).method("start").returningVoid().taking(Stage.class).invokeOn(myApplication, stage);
        } catch (Throwable ex) {
            fail("Cannot use methode start. Error:" + ex.getMessage());
        }

        this.stage = stage;
    }

    @Test
    @Points("gui_basics-1")
    public void applicationTitle() {
        assertEquals("My Application", stage.getTitle());
    }

    @Test
    @Points("gui_basics-1")
    public void showMethod() {
        assertTrue("Cannot invoke the methode show.", stage.isShowing());
    }

}
