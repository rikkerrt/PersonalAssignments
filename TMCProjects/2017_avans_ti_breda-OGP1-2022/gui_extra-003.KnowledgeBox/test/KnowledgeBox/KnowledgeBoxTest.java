package KnowledgeBox;

import KnowledgeBox.KnowledgeBoxApplication;
import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import nl.avans.testhelper.FxRobotListSelection;
import nl.avans.testhelper.TestHelperJavaFX;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.testfx.framework.junit.ApplicationTest;

public class KnowledgeBoxTest extends ApplicationTest implements FxRobotListSelection {

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
        KnowledgeBoxApplication application = new KnowledgeBoxApplication();
        TestHelperJavaFX.testJavaFXStart(application, KnowledgeBoxApplication.class, stage);
        this.stage = stage;
    }

    
    @Test
    @Points("gui_extra-3.1")
    public void TestUserInterface() {
        // Find input fields
        List<Button> buttons = new ArrayList<Button>();
        List<ComboBox> comboBoxes = new ArrayList<ComboBox>();
        List<ListView> listViews = new ArrayList<ListView>();
        testGUI(buttons, comboBoxes, listViews);

    }
    
    @Test
    @Points("gui_extra-3.2")
    public void TestFunctionallity() {
        // Find input fields
        List<Button> buttons = new ArrayList<Button>();
        List<ComboBox> comboBoxes = new ArrayList<ComboBox>();
        List<ListView> listViews = new ArrayList<ListView>();
        testGUI(buttons, comboBoxes, listViews);
        
        // Check excisting
        
        String comboBoxItems[] = { "Java", "Hardware Interfacing", "P&OC", "UML", "Wiskunde"};  
        
        boolean found = true;
        for (String item : comboBoxItems) {
            if (!comboBoxes.get(0).getItems().contains(item)) {
                found = false;
                fail("The combobox doesn't contain the item: " + item);
            }
        }
        
        // Simple test
        addItemToListView(buttons.get(0), comboBoxes.get(0), listViews.get(0), comboBoxItems[0]);
        addItemToListView(buttons.get(0), comboBoxes.get(0), listViews.get(0), comboBoxItems[1]);
        removeItemFromListView(buttons.get(1), listViews.get(0), comboBoxItems[1]);
        
        // Test duplicate item
        addItemToListView(buttons.get(0), comboBoxes.get(0), listViews.get(0), comboBoxItems[0]);
        int doubleItems = countDoubleItems(listViews.get(0), comboBoxItems[0]);
        assertFalse("The ListView should not contain duplicate items", doubleItems>1);
        removeItemFromListView(buttons.get(1), listViews.get(0), comboBoxItems[0]);
        
    }
    
    private int countDoubleItems(ListView<String> listView, String item){
        
        int count = 0;
        for (String currItem : listView.getItems()) {
            if (currItem.equals(item))
                count++;
        }
        
        return count;
    }
    
    private void addItemToListView(Button addButton, ComboBox combobox, ListView listView, String item) {
        selectGivenComboBoxItem(combobox, item);
        clickOn(addButton);
        assertTrue("The listview should contain the item: " + item, listView.getItems().contains(item)); 
    }
    
    private void removeItemFromListView(Button removeButton, ListView listView, String item) {
        selectGivenListViewItem(listView, item);
        clickOn(removeButton);
        assertFalse("The listview should not contain the item: " + item, listView.getItems().contains(item)); 
    }
    
    private void testGUI(List<Button> buttons, List<ComboBox> comboBoxes, List<ListView> listViews) {
        // Basic tests
        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now that the start-method has been executed,the getScene returned the null reference.", scene);
        Parent parent = scene.getRoot();
        assertNotNull("The scene object should be given a parameter for the layout of the user interface components (here BorderPane). No object containing components was found in the Scene Object.", parent);
       
        // Find buttons
        buttons.addAll(TestHelperJavaFX.getAllNodesOfType(parent, Button.class));
        assertEquals("The application should contain 2 buttons ", 2, buttons.size());
        assertEquals("Name of the first button should be Larger ", "Add skill", buttons.get(0).getText());
        assertEquals("Name of the second button should be Smaller ", "Remove skill", buttons.get(1).getText());
        
        // Find ComboBox
        comboBoxes.addAll(TestHelperJavaFX.getAllNodesOfType(parent, ComboBox.class));
        assertEquals("The application should contain 1 ComboBox ", 1, comboBoxes.size());
        
        // Find ListView
        listViews.addAll(TestHelperJavaFX.getAllNodesOfType(parent, ListView.class));
        assertEquals("The application should contain 1 ListView ", 1, listViews.size());
        
    }
    

}
