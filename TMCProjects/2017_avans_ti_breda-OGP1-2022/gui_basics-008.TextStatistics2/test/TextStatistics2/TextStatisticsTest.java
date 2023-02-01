package TextStatistics2;

import TextStatistics2.TextStatisticsApplication;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.testfx.framework.junit.ApplicationTest;

public class TextStatisticsTest extends ApplicationTest {

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
        TextStatisticsApplication application = new TextStatisticsApplication();

        try {
            Application app = Application.class.cast(application);
        } catch (Throwable t) {
            fail("MyApplication doesn't implement the Application interface.");
        }

        try {
            Reflex.reflect(TextStatisticsApplication.class).method("start").returningVoid().taking(Stage.class).invokeOn(application, stage);
        } catch (Throwable ex) {
            fail("Cannot use methode start. Error:" + ex.getMessage());
        }

        this.stage = stage;
    }

    @Test
    @Points("gui_basics-8")
    public void changingStatisticsOne() {
        writeAndCheck("You miss 100 percent of the shots you never take. - Gretzky");
    }

    @Test
    @Points("gui_basics-8")
    public void changingStatisticsTwo() {
        writeAndCheck("We are what we repeatedly do; excellence, then, is not an act but a habit. - Aristotle");
    }
    
    @Test
    @Points("gui_basics-8")
    public void changingStatisticsThree() {
        writeAndCheck("You must be the change you wish to see in the world. - Gandhi");
    }

    private void writeAndCheck(String inputText) {
        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now that the start-method has been executed,the getScene returned the null reference.", scene);
        Parent parent = scene.getRoot();
        assertNotNull("The scene object should be given a parameter for the layout of the user interface components (here BorderPane). No object containing components was found in the Scene Object.", parent);

        BorderPane borderPane = null;
        try {
            borderPane = BorderPane.class.cast(parent);
        } catch (Throwable t) {
            fail("Use the BorderPane class to set the user interface components.");
        }

        assertNotNull("The Scene Object should be given the BorderPane object as user interface components.", borderPane);
        assertTrue("The BorderPane should be placed in the middle of the TextArea object. Now in the middle was: " + borderPane.getCenter(), borderPane.getCenter() != null && borderPane.getCenter().getClass().isAssignableFrom(TextArea.class));
        assertTrue("The BorderPane should be placed in the lower part of the HBox. Now the bottom part was: " + borderPane.getBottom(), borderPane.getBottom() != null && borderPane.getBottom().getClass().isAssignableFrom(HBox.class));

        clickOn(borderPane.getCenter());
        write(inputText);

        HBox box = (HBox) borderPane.getBottom();
        assertEquals("It was expected that there are three text elements at the bottom. Now the elements were: " + box.getChildren().size(), 3, box.getChildren().size());

        List<Node> nodes = box.getChildren();
        for (Node node : nodes) {
            assertTrue("Elements added to the HBox object should be Label component. not found in: " + node, node.getClass().isAssignableFrom(Label.class));
        }

        int textLength = inputText.length();
        int wordLength = inputText.split(" ").length;
        String longestWord = Arrays.stream(inputText.split(" "))
                .sorted((s1, s2) -> s2.length() - s1.length())
                .findFirst()
                .get();

        assertEquals("The first text element should have the text \"Letters: \"" + textLength + "\". Now the text was: \"" + ((Label) nodes.get(0)).getText() + "\"", "Letters: " + textLength, ((Label) nodes.get(0)).getText());
        assertEquals("The second text element should have the text  \"Words: " + wordLength + "\". Now the text was: \"" + ((Label) nodes.get(1)).getText() + "\"", "Words: " + wordLength, ((Label) nodes.get(1)).getText());
        assertEquals("The third text element should have the text  \"The longest word is: " + longestWord + "\". Now the text was: \"" + ((Label) nodes.get(2)).getText() + "\"", "The longest word is: " + longestWord, ((Label) nodes.get(2)).getText().trim());

    }

}
