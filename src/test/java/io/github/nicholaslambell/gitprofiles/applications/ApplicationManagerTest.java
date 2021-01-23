package io.github.nicholaslambell.gitprofiles.applications;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationManagerTest {
    //region Mode Detection
    @Test
    void testForcedTerminal() {
        String[] args = { "terminal" };
        ApplicationManager applicationManager = new ApplicationManager(args);

        assertEquals(applicationManager.getApplicationMode(), ApplicationMode.TERMINAL);
    }

    @Test
    void testForcedInterface() {
        String[] args = { "interface" };
        ApplicationManager applicationManager = new ApplicationManager(args);

        assertEquals(applicationManager.getApplicationMode(), ApplicationMode.INTERFACE);
    }

    @Test
    void testDetectedTerminal() {
        String[] args = { "use", "example" };
        ApplicationManager applicationManager = new ApplicationManager(args);

        assertEquals(applicationManager.getApplicationMode(), ApplicationMode.TERMINAL);
    }

    @Test
    void testDetectedInterface() {
        String[] args = {};
        ApplicationManager applicationManager = new ApplicationManager(args);

        assertEquals(applicationManager.getApplicationMode(), ApplicationMode.INTERFACE);
    }
    //endregion
}
