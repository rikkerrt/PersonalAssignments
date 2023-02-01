package MyApplication;

import MyApplication.MyApplication;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import javafx.application.Application;
import javafx.stage.Stage;
import static org.junit.Assert.fail;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isNull;
import org.testfx.matcher.control.LabeledMatchers;

@Points("gui_multiple-2")
public class GreetingTest extends ApplicationTest {

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
            fail("Cannot use method start. Error:" + ex.getMessage());
        }

        this.stage = stage;
    }

    @Test
    public void welcomeToJohan() {
        checkWelcome("Johan Talboom");
    }

    @Test
    public void welcomeToEtienne() {
        checkWelcome("Etienne Goossens");
    }
    
    @Test
    public void welcomeToMaurice() {
        checkWelcome("Maurice Snoeren");
    }

    private void checkWelcome(String name) {
        clickOn(".text-field").write(name);
        clickOn(".button");
        verifyThat(".label", LabeledMatchers.hasText("Welcome " + name + "!"));
        verifyThat(".text-field", isNull());
        verifyThat(".button", isNull());

    }
}
