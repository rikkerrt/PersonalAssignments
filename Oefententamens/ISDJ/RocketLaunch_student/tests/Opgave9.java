import com.sun.java.swing.plaf.windows.resources.windows;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Window;
import nl.avans.testhelper.TestHelperJavaFX;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.List;

public class Opgave9 extends ApplicationTest
{
    private javafx.stage.Stage stage;

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

    public void start(javafx.stage.Stage stage) throws Exception {
        Object application = null;
        try {
            application = Reflex.reflect("RocketGui").constructor().takingNoParams().invoke();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        TestHelperJavaFX.testJavaFXStart(application, Reflex.reflect("RocketGui").cls(), stage);
        this.stage = stage;
    }

    @Test
    public void testEmpty()
    {
        ArrayList<Button> buttons = new ArrayList<>();

        Scene scene = stage.getScene();
        Assert.assertNotNull( scene);
        Parent parent = scene.getRoot();
        Assert.assertNotNull( parent);

        buttons.addAll(TestHelperJavaFX.getAllNodesOfType(parent, Button.class));

        clickOn(buttons.get(1));
        List<Window> windows = robotContext().getWindowFinder().listTargetWindows();
        List<Label> labels = TestHelperJavaFX.getAllNodesOfType(windows.get(1).getScene().getRoot(), Label.class);
        Assert.assertTrue( labels.get(0).getText().toLowerCase().contains("error"));
        clickOn(TestHelperJavaFX.getAllNodesOfType(windows.get(1).getScene().getRoot(), Button.class).get(0));
    }

    @Test
    @Points("4")
    public void testFuels() throws Throwable {
        ArrayList<Button> buttons = new ArrayList<>();
        ArrayList<RadioButton> radiobuttons = new ArrayList<>();
        ArrayList<ListView> listviews = new ArrayList<>();
        ArrayList<TextField> textfields = new ArrayList<>();

        Scene scene = stage.getScene();
        Assert.assertNotNull( scene);
        Parent parent = scene.getRoot();
        Assert.assertNotNull( parent);

        buttons.addAll(TestHelperJavaFX.getAllNodesOfType(parent, Button.class));
        radiobuttons.addAll(TestHelperJavaFX.getAllNodesOfType(parent, RadioButton.class));
        listviews.addAll(TestHelperJavaFX.getAllNodesOfType(parent, ListView.class));
        textfields.addAll(TestHelperJavaFX.getAllNodesOfType(parent, TextField.class));

        Assert.assertEquals( 2, radiobuttons.size());
        Assert.assertEquals( 3, buttons.size());
        Assert.assertEquals( 1, listviews.size());
        Assert.assertEquals( 1, textfields.size());

        clickOn(radiobuttons.get(0));
        textfields.get(0).clear();
        clickOn(textfields.get(0));
        write("100");
        clickOn(buttons.get(0));

        clickOn(radiobuttons.get(1));
        textfields.get(0).clear();
        clickOn(textfields.get(0));
        write("50");
        clickOn(buttons.get(0));

        clickOn(buttons.get(1));

        List<Window> windows = robotContext().getWindowFinder().listTargetWindows();
        List<Label> labels = TestHelperJavaFX.getAllNodesOfType(windows.get(1).getScene().getRoot(), Label.class);
        Assert.assertTrue( labels.get(1).getText().contains("2955"));

        clickOn(TestHelperJavaFX.getAllNodesOfType(windows.get(1).getScene().getRoot(), Button.class).get(0));
    }
}
