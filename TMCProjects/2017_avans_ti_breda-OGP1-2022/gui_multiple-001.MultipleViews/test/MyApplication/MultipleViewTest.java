package MyApplication;

import MyApplication.MultipleViews;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

@Points("gui_multiple-1")
public class MultipleViewTest extends ApplicationTest {

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
        MultipleViews application = new MultipleViews();

        try {
            Application app = Application.class.cast(application);
        } catch (Throwable t) {
            fail("MyApplication doesn't implement the Application interface.");
        }

        try {
            Reflex.reflect(MultipleViews.class).method("start").returningVoid().taking(Stage.class).invokeOn(application, stage);
        } catch (Throwable ex) {
            fail("Cannot use method start. Error:" + ex.getMessage());
        }

        this.stage = stage;
    }

    @Test
    public void firstView() {
        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now that the start-method has been executed, the method getScene returned the null reference.", scene);
        Parent parent = scene.getRoot();
        assertNotNull("The Scene Object corresponding to the first view should be given a parameter for the layout of the user interface components (here BorderPane). No object containing components was found in the Scene Object.", parent);

        BorderPane borderPane = null;
        try {
            borderPane = BorderPane.class.cast(parent);
        } catch (Throwable t) {
            fail("Use the BorderPane category for the first view as user interface components.");
        }

        assertNotNull("The Scene Objective responsible for the first view should be given the BorderPane object for parameterizing the user interface components.", borderPane);
        assertTrue("BorderPanen should have a Button in the middle. Now there was: " + borderPane.getCenter(), borderPane.getCenter().getClass().isAssignableFrom(Button.class));
        assertTrue("At the top of BorderPanen, there should be a label item. Now there was: " + borderPane.getTop(), borderPane.getTop().getClass().isAssignableFrom(Label.class));

        clickOn(".button");
        Scene toka = stage.getScene();
        assertNotEquals("When the user interface button is pressed, the displayed view must chaging. Now, after pressing the button, the Scene Object of the Stage was the same as before pressing.", scene, toka);
    }

    @Test
    public void secondView() {
        clickOn(".button");

        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now after pushing a button, the method getScene returned a null reference.", scene);
        Parent borderPane = scene.getRoot();
        assertNotNull("The Scene Object corresponding to the second view must be given a parameter for the layout of the user interface components (here VBox). No object containing components was found in the Scene Object.", borderPane);

        VBox vbox = null;
        try {
            vbox = VBox.class.cast(borderPane);
        } catch (Throwable t) {
            fail("Use the VBox category to set the user interface components for the second view.");
        }

        assertNotNull("The Scene Objective responsible for the second view must be given a VBox object for the layout of the user interface components.", vbox);

        assertEquals("The VBox should have two components added. Now they were: " + vbox.getChildren().size(), 2, vbox.getChildren().size());

        assertTrue("The first item in the VBox object should be a Button. Now there was: " + vbox.getChildren().get(0), vbox.getChildren().get(0).getClass().isAssignableFrom(Button.class));
        assertTrue("The second element of the VBox entity should be a Label object. Now there was: " + vbox.getChildren().get(1), vbox.getChildren().get(1).getClass().isAssignableFrom(Label.class));

        clickOn(".button");
        Scene newScene = stage.getScene();
        assertNotEquals("When the user interface button is pressed, the displayed view must alternate. Now after pressing a button in the second view, the Scene Object of the Stage object was the same as before pressing.", scene, newScene);
    }

    @Test
    public void thirthView() {
        Scene firstScene = stage.getScene();
        clickOn(".button");
        clickOn(".button");

        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now after pushing a button, the method getScene returned a null reference.", scene);
        Parent parent = scene.getRoot();
        assertNotNull("The Scene Object corresponding to the third view should be given a parameter for the layout of the user interface components (here GridPane). No object containing components was found in the Scene Object.", parent);

        GridPane gridPane = null;
        try {
            gridPane = GridPane.class.cast(parent);
        } catch (Throwable t) {
            fail("Use the GridPane category for the third-view user interface components.");
        }

        assertNotNull("The Scene Objective responsible for the third view should be given a GridPane object as user interfaces.", gridPane);

        assertEquals("GridPane should have two components added. Now they were: " + gridPane.getChildren().size(), 2, gridPane.getChildren().size());

        assertTrue("The first item in GridPane should be a Label object. Now there was: " + gridPane.getChildren().get(0), gridPane.getChildren().get(0).getClass().isAssignableFrom(Label.class));
        assertTrue("The second element in the GridPane element should be a Button. Now there was: " + gridPane.getChildren().get(1), gridPane.getChildren().get(1).getClass().isAssignableFrom(Button.class));

        clickOn(".button");
        Scene secondScene = stage.getScene();
        assertEquals("When you press the button in the third view, you should end up in the first view. The moment the button was pressed was not the same as the first view.", firstScene, secondScene);
    }
}
