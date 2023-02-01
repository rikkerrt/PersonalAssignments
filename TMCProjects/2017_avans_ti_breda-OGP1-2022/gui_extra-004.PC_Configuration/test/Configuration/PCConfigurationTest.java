package Configuration;

import Configuration.PCConfigurationApplication;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nl.avans.testhelper.FxRobotListSelection;
import nl.avans.testhelper.TestHelperJavaFX;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.testfx.framework.junit.ApplicationTest;

public class PCConfigurationTest extends ApplicationTest implements FxRobotListSelection {

    private Stage stage;
    Reflex.ClassRef<Object> PCApplication;
    
    private List<Button> buttons = new ArrayList<Button>();
    private List<ComboBox> comboBoxes = new ArrayList<ComboBox>();
    private List<TableView> tableViews = new ArrayList<TableView>();
    private List<TextField> textFields = new ArrayList<TextField>();

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
    
    @Before
    public void init() {
        PCApplication = Reflex.reflect("Configuration.PCConfigurationApplication");
    }
    

    @Override
    public void start(Stage stage) throws Exception {
        PCConfigurationApplication application = new PCConfigurationApplication();
        
        TestHelperJavaFX.testJavaFXStart(application, PCConfigurationApplication.class, stage);
        this.stage = stage;
    }

    
    @Test
    @Points("gui_extra-4.2")
    public void testUserInterface() {
        // Find input fields
        
        testGUI(buttons, comboBoxes, tableViews, textFields);

    }
    
    @Test
    @Points("gui_extra-4.3")
    public void testAddConfiguration() throws Throwable {
        
        // TODO fix, make sure that we don't have to do this every test
        testGUI(buttons, comboBoxes, tableViews, textFields);
        // todo Check for ObservableList
        
        selectGivenComboBoxItem(comboBoxes.get(0), "Intel Core i9 9900K");
        selectGivenComboBoxItem(comboBoxes.get(1), "GeForce GTX 1070 Ti");
        selectGivenComboBoxItem(comboBoxes.get(2), "128GB DDR4");
        clickOn(textFields.get(0));
        write("Me");
        
        clickOn(buttons.get(0));
        
        // Check TableView
        TableView tv = tableViews.get(0);
        Reflex.ClassRef<Object> Configuration = Reflex.reflect("Configuration.Configuration");

        // TODO reflect on selectedItem to Configuration
        Object configuration = tv.getItems().get(0);
        
        
        String currCPU = Configuration.method("getCpu").returning(String.class).takingNoParams().invokeOn(configuration);
        String currGPU = Configuration.method("getGpu").returning(String.class).takingNoParams().invokeOn(configuration);
        String currMemory = Configuration.method("getMemory").returning(String.class).takingNoParams().invokeOn(configuration);
        String currOwner = Configuration.method("getOwner").returning(String.class).takingNoParams().invokeOn(configuration);
        
        assertEquals("CPU in configuration is not set correctly", "Intel Core i9 9900K", currCPU);
        assertEquals("GPU in configuration is not set correctly", "GeForce GTX 1070 Ti", currGPU);
        assertEquals("Memory in configuration is not set correctly", "128GB DDR4", currMemory);
        assertEquals("Owner in configuration is not set correctly", "Me", currOwner);
          
    }
    
    @Test
    @Points("gui_extra-4.3")
    public void testDisabledAddButton() throws Throwable {
        
        // TODO fix, make sure that we don't have to do this every test
        testGUI(buttons, comboBoxes, tableViews, textFields);
        // todo Check for ObservableList
        
        assertTrue("Button Add configuration should be disabled", buttons.get(0).isDisabled());
        
        selectGivenComboBoxItem(comboBoxes.get(0), "Intel Core i9 9900K");
        assertTrue("Button Add configuration should be disabled", buttons.get(0).isDisabled());
        selectGivenComboBoxItem(comboBoxes.get(1), "GeForce GTX 1070 Ti");
        assertTrue("Button Add configuration should be disabled", buttons.get(0).isDisabled());
        selectGivenComboBoxItem(comboBoxes.get(2), "128GB DDR4");
        assertTrue("Button Add configuration should be disabled", buttons.get(0).isDisabled());
        clickOn(textFields.get(0));
        write("Me");
        
        assertFalse("Button Add configuration should be enabled", buttons.get(0).isDisabled());
              
    }
    
    
    

    
    private void testGUI(List<Button> buttons, List<ComboBox> comboBoxes, List<TableView> tableViews, List<TextField> textFields) {
        // Basic tests
        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now that the start-method has been executed,the getScene returned the null reference.", scene);
        Parent parent = scene.getRoot();
        assertNotNull("The scene object should be given a parameter for the layout of the user interface components (here BorderPane). No object containing components was found in the Scene Object.", parent);
       
        // Find buttons
        buttons.addAll(TestHelperJavaFX.getAllNodesOfType(parent, Button.class));
        assertEquals("The application should contain 1 buttons ", 1, buttons.size());
        assertEquals("Name of the first button should be Add configuration ", "Add configuration", buttons.get(0).getText());
        
        // Find ComboBox
        comboBoxes.addAll(TestHelperJavaFX.getAllNodesOfType(parent, ComboBox.class));
        assertEquals("The application should contain 3 ComboBox ", 3, comboBoxes.size());
        
        // Find tableView
        tableViews.addAll(TestHelperJavaFX.getAllNodesOfType(parent, TableView.class));
        assertEquals("The application should contain 1 TableView ", 1, tableViews.size());
        
        // Find TextField
        textFields.addAll(TestHelperJavaFX.getAllNodesOfType(parent, TextField.class));
        assertEquals("The application should contain 1 TextField ", 1, textFields.size());
        
    }
    

}
