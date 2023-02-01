package RotateImage;

import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import nl.avans.testhelper.TestHelperJavaFX;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.testfx.framework.junit.ApplicationTest;

public class RotateImageTest extends ApplicationTest {

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
        RotateImageApplication application = new RotateImageApplication();
        TestHelperJavaFX.testJavaFXStart(application, RotateImageApplication.class, stage);
        this.stage = stage;
    }

    @Test
    @Points("gui_extra-2.2")
    public void testRotate() {
        List<Button> buttons = new ArrayList<Button>();
        List<ImageView> imageViews = new ArrayList<ImageView>();
        testGUI(buttons, imageViews);
        
        internalRotate(buttons.get(2), imageViews.get(0), 10, true);
        internalRotate(buttons.get(3), imageViews.get(0), 3, true);
    }
    
    
    @Test
    @Points("gui_extra-2.2")
    public void testZoom() {
        List<Button> buttons = new ArrayList<Button>();
        List<ImageView> imageViews = new ArrayList<ImageView>();
        testGUI(buttons, imageViews);
        
        internalZoom(buttons.get(0), imageViews.get(0), 3, true);
        internalZoom(buttons.get(1), imageViews.get(0), 4, false);
    }
    
    private void internalRotate(Button button, ImageView imageView, int times, boolean cw) {
        
        double initialPos = imageView.getRotate();
        
        for (int i = 0; i < times; i++) {
            clickOn(button);
        }
        
        double expectedPos = initialPos;
        if (cw) {
            expectedPos = (double)Math.floorMod((int)expectedPos+(times*45), 360);
        } else {
             expectedPos = (double)Math.floorMod((int)expectedPos-(times*45), 360);
             if (expectedPos < 0)
                 expectedPos += 360;
        }
    }
    
    private void internalZoom(Button button, ImageView imageView, int times, boolean zoomIn) {
        double zoomX = imageView.getScaleX();
        double zoomY = imageView.getScaleY();
        assertEquals("Ratio of scaleX and scaleY should be the same", zoomX, zoomY, 0.01);
        
        // Zoom into the image
        for (int i = 0; i < times; i++) 
            clickOn(button);
        
        double expectedResult = 0.0;
        if (zoomIn) {
            expectedResult = zoomX+(times*0.1);
        } else {
            expectedResult = zoomX-(times*0.1);
        }
        
        double newZoomX = imageView.getScaleX();
        double newZoomY = imageView.getScaleY();
        
        if (expectedResult < 0.0)
            expectedResult = 0.0;
        
        assertEquals("Expected Scale size should be ", newZoomX, expectedResult, 0.01);
        assertEquals("Expected Scale size should be ", newZoomY, expectedResult, 0.01);
        
        assertTrue("Scale should be above 0.0", (newZoomX >= 0.0) && (newZoomY >= 0.0));
        
        // Reset to 1.0
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }
    
    @Test
    @Points("gui_extra-2.1")
    public void TestUserInterface() {
        // Find input fields
        List<Button> buttons = new ArrayList<Button>();
        List<ImageView> imageViews = new ArrayList<ImageView>();
        testGUI(buttons, imageViews);
    }
    
    private void testGUI(List<Button> buttons, List<ImageView> imageViews) {
        // Basic tests
        Scene scene = stage.getScene();
        assertNotNull("The Stage Object should have a Scene Object. Now that the start-method has been executed,the getScene returned the null reference.", scene);
        Parent parent = scene.getRoot();
        assertNotNull("The scene object should be given a parameter for the layout of the user interface components (here BorderPane). No object containing components was found in the Scene Object.", parent);
       
        // Find buttons
        buttons.addAll(TestHelperJavaFX.getAllNodesOfType(parent, Button.class));
        assertEquals("The application should contain 4 buttons ", 4, buttons.size());
        assertEquals("Name of the first button should be Larger ", "Larger", buttons.get(0).getText());
        assertEquals("Name of the second button should be Smaller ", "Smaller", buttons.get(1).getText());
        assertEquals("Name of the thirth button should be Rotate CW ", "Rotate CW", buttons.get(2).getText());
        assertEquals("Name of the fourth button should be Rotate CCW ", "Rotate CCW", buttons.get(3).getText());
        
        // Find ImageView
        imageViews.addAll(TestHelperJavaFX.getAllNodesOfType(parent, ImageView.class));
        assertEquals("The application should contain 1 ImageView ", 1, imageViews.size());
        
    }
    

}
