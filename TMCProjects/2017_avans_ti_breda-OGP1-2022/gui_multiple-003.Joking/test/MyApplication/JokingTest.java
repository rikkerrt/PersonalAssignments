package MyApplication;

import MyApplication.JokingApplication;
import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import javafx.application.Application;
import javafx.stage.Stage;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;

@Points("gui_multiple-3")
public class JokingTest extends ApplicationTest {

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
        JokingApplication application = new JokingApplication();

        try {
            Application app = Application.class.cast(application);
        } catch (Throwable t) {
            fail("MyApplication doesn't implement the Application interface.");
        }

        try {
            Reflex.reflect(JokingApplication.class).method("start").returningVoid().taking(Stage.class).invokeOn(application, stage);
        } catch (Throwable ex) {
            fail("Cannot use method start. Error:" + ex.getMessage());
        }

        this.stage = stage;
    }

    @Test
    public void JokeTest() {
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("What do you call a bear with no teeth?"));
        clickOn("Question");
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("What do you call a bear with no teeth?"));
        clickOn("Answer");
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("A gummy bear."));
        clickOn("Question");
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("What do you call a bear with no teeth?"));
        clickOn("Explanation");
        FxAssert.verifyThat(".label", NodeMatchers.isNotNull());
    }
}
