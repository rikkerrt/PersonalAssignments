package MyApplication;

import MyApplication.MyApplication;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.*;
import static org.junit.Assert.assertNotNull;
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
    @Points("gui_basics-2")
    public void findDesiredElements() {
        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. After calling the start methode, the methode getScene returned a null reference.", scene);
        Parent sceneRoot = scene.getRoot();
        assertNotNull("The Scene Object must be given a parameter for the layout of user interface components (eg FlowPane). No object components where found in the Scene Object.", sceneRoot);
        List<Node> children = new ArrayList(sceneRoot.getChildrenUnmodifiable());
        boolean labelFound = false;
        boolean buttonFound = false;

        while (!children.isEmpty()) {
            Node node = children.get(0);
            if (node instanceof Label) {
                labelFound = true;
            }

            if (node instanceof Button) {
                buttonFound = true;
            }

            if (node instanceof Parent) {
                Parent p = (Parent) node;
                children.addAll(p.getChildrenUnmodifiable());
            }

            children.remove(node);
        }

        assertTrue("There was no Label component found in the Scene Object.", labelFound);
        assertTrue("There was no Button coponent found on the Scene Object", buttonFound);

    }

}
