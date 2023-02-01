package MyApplication;

import MyApplication.MyApplication;
import fi.helsinki.cs.tmc.edutestutils.Points;
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

public class AppTest extends ApplicationTest {

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
        MyApplication application = new MyApplication();

        try {
            Application app = Application.class.cast(application);
        } catch (Throwable t) {
            fail("MyApplication doesn't implement the Application interface.");
        }

        try {
            Reflex.reflect(MyApplication.class).method("start").returningVoid().taking(Stage.class).invokeOn(application, stage);
        } catch (Throwable ex) {
            fail("Cannot use methode start. Error:" + ex.getMessage());
        }

        this.stage = stage;
    }

    @Test
    @Points("gui_basics-6")
    public void FindingDesiredElements() {
        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now that the start-method has been executed, the method getScene returned the null reference.", scene);
        Parent parent = scene.getRoot();
        assertNotNull("A Scene Object should be given as a parameter for the purpose of setting user interface components (here VBox). No object containing components was found in the Scene Object.", parent);

        VBox vbox = null;
        try {
            vbox = VBox.class.cast(parent);
        } catch (Throwable t) {
            fail("Use the VBox category to set the user interface components.");
        }

        assertNotNull("The Scene Object should be given a VBox object for setting user interface components.", vbox);

        assertEquals("It was expected that the user interface will have three text elements. Now the elements were: " + vbox.getChildren().size(), 3, vbox.getChildren().size());

        Node first = vbox.getChildren().get(0);
        Node second = vbox.getChildren().get(1);
        Node thirth = vbox.getChildren().get(2);
        assertTrue("The element added to the first VBox object should be a TextField object. Now it was not. found in: " + first, first.getClass().isAssignableFrom(TextField.class));
        assertTrue("The element added to another VBox object should be a Button Oil. Now it was not. found in: " + second, second.getClass().isAssignableFrom(Button.class));
        assertTrue("The element added to the third VBox object should be a Label object. Now it was not. found in: " + thirth, thirth.getClass().isAssignableFrom(Label.class));

        TextField testField = (TextField) vbox.getChildren().get(0);
        Button button = (Button) vbox.getChildren().get(1);
        Label label = (Label) vbox.getChildren().get(2);

        testField.setText("Hello world!");

        clickOn(button);

        assertEquals("When the text \"Hello world!\" Is in the text box and the button is pressed, the same text should be copied to the text element. Now the text element was: " + label.getText(), "Hello world!", label.getText());

        testField.setText("I am a mummy!");
        clickOn(button);
        assertEquals("When the text \"I am a mummy!\" Appears in the text box and the button is pressed, the same text should be copied to the text element. Now the text element was: " + label.getText(), "I am a mummy!", label.getText());
    }
}
