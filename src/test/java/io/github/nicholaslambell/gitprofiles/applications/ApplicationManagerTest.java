package io.github.nicholaslambell.gitprofiles.applications;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationManagerTest {
    //region Mode Detection
    @Test
    public void testForcedTerminal() {
        String[] args = { "terminal" };
        ApplicationManager applicationManager = new ApplicationManager(args);

        assertEquals(applicationManager.getApplicationMode(), ApplicationMode.TERMINAL);
    }

    @Test
    public void testForcedInterface() {
        String[] args = { "interface" };
        ApplicationManager applicationManager = new ApplicationManager(args);

        assertEquals(applicationManager.getApplicationMode(), ApplicationMode.INTERFACE);
    }

    @Test
    public void testDetectedTerminal() {
        String[] args = { "use", "example" };
        ApplicationManager applicationManager = new ApplicationManager(args);

        assertEquals(applicationManager.getApplicationMode(), ApplicationMode.TERMINAL);
    }

    @Test
    public void testDetectedInterface() {
        String[] args = {};
        ApplicationManager applicationManager = new ApplicationManager(args);

        assertEquals(applicationManager.getApplicationMode(), ApplicationMode.INTERFACE);
    }
    //endregion
}
