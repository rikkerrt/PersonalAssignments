package Calculator;

import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.avans.testhelper.TestHelperJavaFX;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.testfx.framework.junit.ApplicationTest;

public class CalculatorTest extends ApplicationTest {

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
        CalculatorApplication application = new CalculatorApplication();
        TestHelperJavaFX.testJavaFXStart(application, CalculatorApplication.class, stage);
        this.stage = stage;
    }

    @Test
    @Points("gui_extra-1.2")
    public void TestCalculation1() {
        WriteAndCheck(10.0, 10.0);
    }
    
    @Test
    @Points("gui_extra-1.2")
    public void TestCalculation2() {
        WriteAndCheck(20.0, 1.5);
    }
    
    @Test
    @Points("gui_extra-1.1")
    public void TestUserInterface() {
        // Find input fields
        List<TextField> textFields = new ArrayList<TextField>();
        List<RadioButton> radioButtons = new ArrayList<RadioButton>();
        testGUI(textFields, radioButtons);
    }
    
    private void testGUI(List<TextField> textFields, List<RadioButton> radioButtons) {
        // Basic tests
        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now that the start-method has been executed,the getScene returned the null reference.", scene);
        Parent parent = scene.getRoot();
        assertNotNull("The scene object should be given a parameter for the layout of the user interface components (here BorderPane). No object containing components was found in the Scene Object.", parent);
       
        // Find input fields
        textFields.addAll(TestHelperJavaFX.getAllNodesOfType(parent, TextField.class));
        assertEquals("The application should contain 3 TextFields ", 3, textFields.size());
        
        // Find RadioButtons
        radioButtons.addAll(TestHelperJavaFX.getAllNodesOfType(parent, RadioButton.class));
        assertEquals("The application should contain 4 RadioButtons ", 4, radioButtons.size());
        
         // Check RadioButtons
        assertEquals("First RadioButton should have the text Addition", "Addition", radioButtons.get(0).getText());
        assertEquals("Second RadioButton should have the text Substraction", "Substraction", radioButtons.get(1).getText());
        assertEquals("Thirth RadioButton should have the text Multiplication", "Multiplication", radioButtons.get(2).getText());
        assertEquals("Fourth RadioButton should have the text Division", "Division", radioButtons.get(3).getText());
    }
    
    private void WriteAndCheck(double number1, double number2) {
        
        // Find input fields
        List<TextField> textFields = new ArrayList<>();
        List<RadioButton> radioButtons = new ArrayList<>();
        testGUI(textFields, radioButtons);
       
        // Set input
        textFields.get(0).setText(""+number1);
        textFields.get(1).setText(""+number2);
        
        // Click & Calculate :)
        clickOn(radioButtons.get(0).getText());
        assertEquals("Result should be ", ""+(number1+number2), textFields.get(2).getText());
        clickOn(radioButtons.get(1).getText());
        assertEquals("Result should be ", ""+(number1-number2), textFields.get(2).getText());
        clickOn(radioButtons.get(2).getText());
        assertEquals("Result should be ", ""+(number1*number2), textFields.get(2).getText());
        clickOn(radioButtons.get(3).getText());
        assertEquals("Result should be ", ""+(number1/number2), textFields.get(2).getText());       
        
    }

}
