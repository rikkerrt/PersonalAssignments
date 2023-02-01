package MyApplication;

import MyApplication.TicTacToeApplication;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.testfx.framework.junit.ApplicationTest;

public class TicTacToeTest extends ApplicationTest {

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
        TicTacToeApplication sovellus = new TicTacToeApplication();

        try {
            Application app = Application.class.cast(sovellus);
        } catch (Throwable t) {
            fail("Period category with Cross Application Application.");
        }

        try {
            Reflex.reflect(TicTacToeApplication.class).method("start").returningVoid().taking(Stage.class).invokeOn(sovellus, stage);
        } catch (Throwable ex) {
            fail("After all, in Classroom, the Application method start, which gets a Stage item as a parameter. If so, check that the method works. Error:" + ex.getMessage());
        }

        this.stage = stage;
    }

    @Test
    @Points("gui_basics-7.1")
    public void testGUI() {
        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now that the start-method has been executed, the invocation made after the spell got getScene returned the null reference.", scene);
        Parent parent = scene.getRoot();
        assertNotNull("The scene object should be given a parameter for the layout of the user interface components (here BorderPane). No object containing components was found in the Scene Object.", parent);

        BorderPane borderpane = null;
        try {
            borderpane = BorderPane.class.cast(parent);
        } catch (Throwable t) {
            fail("Use the BorderPane class to set the user interface components.");
        }

        assertNotNull("The Scene Object should be given the BorderPane object for parameterizing user interface components..", borderpane);
        assertTrue("At the top of BorderPane, there should be a label item. Now there was: " + borderpane.getTop(), borderpane.getTop().getClass().isAssignableFrom(Label.class));
        assertTrue("There should be GridPane in the middle of BorderPane. Now there was: " + borderpane.getCenter(), borderpane.getCenter().getClass().isAssignableFrom(GridPane.class));

        GridPane gridpane = null;
        try {
            gridpane = GridPane.class.cast(borderpane.getCenter());
        } catch (Throwable t) {
            fail("Use the GridPane class in the middle of BorderPane.");
        }

        assertNotNull("Use the GridPane class in the middle of BorderPanes. Now in the middle" + gridpane, gridpane);

        assertEquals("It was expected that the gridpan has nine buttons. Now the elements were:" + gridpane.getChildren().size(), 9, gridpane.getChildren().size());

        for (Node node : gridpane.getChildren()) {
            try {
                Button button = Button.class.cast(node);
            } catch (Throwable t) {
                fail("It was expected that every element in the grid is Button. Now it was not. Error: " + t.getMessage());
            }
        }

    }

    @Test
    @Points("gui_basics-7.2")
    public void testButton() {
        internalButtonTest(0, 1);
    }

    @Test
    @Points("gui_basics-7.2")
    public void testButton2() {
        internalButtonTest(5, 2);
    }

    @Test
    @Points("gui_basics-7.2")
    public void testButton3() {
        internalButtonTest(4, 7);
    }

    private void internalButtonTest(int first, int second) {
        Label label = checkLabel();
        assertEquals("At the beginning of the game text should display: \"Turn: X\". Right now: \"" + label.getText() + "\".", "Turn: X", label.getText());
        List<Button> buttons = getButtons();
        assertTrue("The game should have 9 buttons. Now there are " + buttons.size() + " buttons", buttons.size() == 9);

        assertTrue("When the game starts, the buttons should not have text. Text was now: " + buttons.get(first).getText(), buttons.get(first).getText().trim().isEmpty());
        clickOn(buttons.get(first));
        assertTrue("When the button is clicked and is X's turn, the button's text should become X. Now the button's text became: " + buttons.get(first).getText(), buttons.get(first).getText().trim().equals("X"));

        label = checkLabel();
        assertEquals("When X's turn is played, the next should be O's turn. The text field should read the text \"Turn: O\". Right now: \"" + label.getText() + "\".", "Turn: O", label.getText());
        clickOn(buttons.get(second));
        assertTrue("When the button is clicked and is O's turn, the button's text should become O. Now the text became: " + buttons.get(second).getText(), buttons.get(second).getText().trim().equals("O"));
        label = checkLabel();
        assertEquals("When O's turn is played, the next should be X's turn. The text field should display the text\"Turn: X\". Right now: \"" + label.getText() + "\".", "Turn: X", label.getText());
    }

    @Test
    @Points("gui_basics-7.2")
    public void testButtonReaction() {
        internalTestButtonReaction(0);
    }

    @Test
    @Points("gui_basics-7.2")
    public void testButtonReaction2() {
        internalTestButtonReaction(5);
    }

    @Test
    @Points("gui_basics-7.2")
    public void testButtonReaction3() {
        internalTestButtonReaction(8);
    }

    @Test
    @Points("gui_basics-7.3")
    public void testGamePlay() {
        Label topLabel = checkLabel();
        assertEquals("At the beginning of the game text should be \"Turn: X\". But right now: \"" + topLabel.getText() + "\".", "Turn: X", topLabel.getText());
        List<Button> buttons = getButtons();
        assertTrue("The game should have 9 buttons. Now there are " + buttons.size() + "buttons", buttons.size() == 9);
        Collections.shuffle(buttons);

        String turn = "X";
        for (int i = 0; i < buttons.size(); i++) {
            clickOn(buttons.get(i));
            assertTrue("When the button is clicked and is " + turn + ": the button's text should become: " + turn + ". Right now: " + buttons.get(i).getText(), buttons.get(i).getText().trim().equals(turn));

            String nextTurn = turn;
            turn = turn.equals("X") ? "O" : "X";
            topLabel = checkLabel();
            if (topLabel.getText().equals("End!")) {
                return;
            }

            if (i == 8) {
                fail("When the game is finished, the text field should have text: \"End!\". But right now: \"" + topLabel.getText() + "\".");
            }
            
            if(!topLabel.getText().toLowerCase().contains("turn")) {
                assertEquals("Make sure the end of the game is expressed by text  \"End!\". But right now: \"" + topLabel.getText() + "\".", "Loppu!", topLabel.getText());
            }

            assertEquals("When played " + nextTurn + "the next turn should be Next turn: " + turn + ". But right now: \"" + topLabel.getText() + "\".", "Turn: " + turn, topLabel.getText());
        }
        
        topLabel = checkLabel();
        if (!topLabel.getText().equals("End!")) {
            fail("When the game ends, the text field should be displayed: \"End!\". But right now: \"" + topLabel.getText() + "\"");
        }
    }

    private void internalTestButtonReaction(int kohta) {
        Label label = checkLabel();
        assertEquals("At the beginning of the game text should be \"Turn: X\". Right now: \"" + label.getText() + "\".", "Turn: X", label.getText());
        List<Button> buttons = getButtons();
        assertTrue("The game should have 9 buttons. Now there are " + buttons.size() + " buttons", buttons.size() == 9);

        assertTrue("When the game starts, the buttons should not have any text. Currently: " + buttons.get(kohta).getText(), buttons.get(kohta).getText().trim().isEmpty());
        clickOn(buttons.get(kohta));
        label = checkLabel();
        assertTrue("When the button is clicked and it's X's turn, the button's text should become X. Now the text came: " + buttons.get(kohta).getText(), buttons.get(kohta).getText().trim().equals("X"));

        assertEquals("When X's turn is played, the next should be O's turn. The text field should display the text \"Turn: O\". But now: \"" + label.getText() + "\".", "Turn: O", label.getText());
        clickOn(buttons.get(kohta));
        label = checkLabel();
        assertTrue("When the button you are already playing is clicked and it is O's turn, the button's text should not change and the turn should still be O: it should be O. Now the text came: " + buttons.get(kohta).getText(), buttons.get(kohta).getText().trim().equals("X"));
        assertEquals("When O is clicked on the reserved item, the turn should not change. The text field still has to read the text \"Turn: O\".But now: \"" + label.getText() + "\".", "Turn: O", label.getText());

    }

    private Label checkLabel() {
        Scene scene = stage.getScene();
        if (scene == null) {
            fail("The Scene Object was not found inside the Stage object.");
        }

        Parent parent = scene.getRoot();
        if (parent == null) {
            fail("The parameter of the Scene-element constructor was set to a null reference.");
        }

        BorderPane borderpane = null;
        try {
            borderpane = BorderPane.class.cast(parent);
        } catch (Throwable t) {
            fail("The BorderPane class was not used for layout of user interface components.");
        }

        if (borderpane.getTop() == null || !borderpane.getTop().getClass().isAssignableFrom(Label.class)) {
            fail("No Label object was found at the top of the BorderPanen in the user interface.");
        }

        return (Label) borderpane.getTop();
    }

    private List<Button> getButtons() {
        Scene scene = stage.getScene();
        if (scene == null) {
            fail("The Scene Object was not found inside the Stage object.");
        }

        Parent parent = scene.getRoot();
        if (parent == null) {
            fail("The parameter of the Scene-element constructor was set to a null reference.");
        }

        BorderPane borderpane = null;
        try {
            borderpane = BorderPane.class.cast(parent);
        } catch (Throwable t) {
            fail("The BorderPane class was not used for layout of user interface components.");
        }

        if (borderpane.getTop() == null || !borderpane.getCenter().getClass().isAssignableFrom(GridPane.class)) {
            fail("There was no GridPane object in the middle of the BorderPanen in the user interface.");
        }

        GridPane gridpane = (GridPane) borderpane.getCenter();
        assertEquals("It was expected that the gridpan has nine buttons. Now there are " + gridpane.getChildren().size() + " elements", 9, gridpane.getChildren().size());
        List<Button> buttons = new ArrayList<>();

        for (Node node : gridpane.getChildren()) {
            try {
                Button button = Button.class.cast(node);
                buttons.add(button);
            } catch (Throwable t) {
                fail("It was expected that every element in the grid is Button. Now it was not. Error:" + t.getMessage());
            }
        }

        return buttons;
    }
}
