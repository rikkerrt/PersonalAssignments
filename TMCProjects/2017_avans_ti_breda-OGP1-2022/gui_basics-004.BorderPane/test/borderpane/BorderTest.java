package borderpane;

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.testfx.framework.junit.ApplicationTest;

public class BorderTest extends ApplicationTest {

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
        BorderPaneApplication app = new BorderPaneApplication();

        try {
            Application application = Application.class.cast(app);
        } catch (Throwable t) {
            fail("MyApplication doesn't implement the Application interface.");
        }

        try {
            Reflex.reflect(BorderPaneApplication.class).method("start").returningVoid().taking(Stage.class).invokeOn(app, stage);
        } catch (Throwable ex) {
            fail("Cannot use methode start. Error:" + ex.getMessage());
        }

        this.stage = stage;
    }

    @Test
    @Points("gui_basics-4")
    public void FindingDesiredElements() {
        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now that the start-method has been executed, the invocation made the getScene returned the null reference.", scene);
        Parent parent = scene.getRoot();
        assertNotNull("The scene object should be given a parameter for the layout of the user interface components (here BorderPane). No object containing components was found in the Scene Object.", parent);

        BorderPane borderPane = null;
        try {
            borderPane = BorderPane.class.cast(parent);
        } catch (Throwable t) {
            fail("Use the BorderPane class to set the user interface components.");
        }

        assertNotNull("The Scene Object should have the BorderPane object for user interface components.", borderPane);
        assertTrue("The BorderPane should have a text field placed at the top. Now at the top was: " + borderPane.getTop(), borderPane.getTop() != null && borderPane.getTop().getClass().isAssignableFrom(Label.class));
        assertTrue("The BorderPane should be placed in the right field with a text field. Now on the right side was: " + borderPane.getRight(), borderPane.getRight() != null && borderPane.getRight().getClass().isAssignableFrom(Label.class));
        assertTrue("The BorderPane should have a text box placed at the bottom. Now the bottom part was: " + borderPane.getBottom(), borderPane.getBottom() != null && borderPane.getBottom().getClass().isAssignableFrom(Label.class));

        assertEquals("The text \"NORTH\" should appear on the top. Now the text was: " + ((Label) borderPane.getTop()).getText(), "NORTH", ((Label) borderPane.getTop()).getText());
        assertEquals("The right side should have the text \"EAST\". Now the text was: " + ((Label) borderPane.getRight()).getText(), "EAST", ((Label) borderPane.getRight()).getText());
        assertEquals("The text \"SOUTH \" must be in the bottom. Now the text was: " + ((Label) borderPane.getBottom()).getText(), "SOUTH", ((Label) borderPane.getBottom()).getText());
    }

}
