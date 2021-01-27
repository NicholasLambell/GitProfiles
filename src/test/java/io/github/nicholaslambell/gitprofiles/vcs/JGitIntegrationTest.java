package io.github.nicholaslambell.gitprofiles.vcs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JGitIntegrationTest {
    @TempDir
    public File testDirectory;

    private final String _testPropertyKeyInvalid = "testing.property";
    private final String _testPropertyKey = "user.name";
    private final String _testPropertyValue = "Example";

    IVCSIntegration _vcsIntegration;

    @BeforeEach
    public void setUp() throws IOException {
        _vcsIntegration = new JGitIntegration(testDirectory);
        _vcsIntegration.setProperty(_testPropertyKey, _testPropertyValue);
    }

    @Test
    public void testGetDirectory() {
        assertEquals(testDirectory, _vcsIntegration.getDirectory());
    }

    @Test
    public void testGetPropertyValid() {
        assertEquals(_testPropertyValue, _vcsIntegration.getProperty(_testPropertyKey));
    }

    @Test
    public void testGetPropertyInvalid() {
        assertNull(_vcsIntegration.getProperty(_testPropertyKeyInvalid));
    }

    @Test
    public void testPropertyExistsValid() {
        assertTrue(_vcsIntegration.propertyExists(_testPropertyKey));
    }

    @Test
    public void testPropertyExistsInvalid() {
        assertFalse(_vcsIntegration.propertyExists(_testPropertyKeyInvalid));
    }

    @Test
    public void testIsGlobalPropertyFalse() {
        assertFalse(_vcsIntegration.isGlobalProperty(_testPropertyKey));
    }

    @Test
    public void testIsGlobalPropertyInvalid() {
        assertFalse(_vcsIntegration.isGlobalProperty(_testPropertyKeyInvalid));
    }

    @Test
    public void testIsLocalPropertyTrue() {
        assertTrue(_vcsIntegration.isLocalProperty(_testPropertyKey));
    }

    @Test
    public void testIsLocalPropertyInvalid() {
        assertFalse(_vcsIntegration.isLocalProperty(_testPropertyKeyInvalid));
    }

    @Test
    public void testSetProperty() {
        String testKey = "testing.change";
        String testValue = "New Value";

        assertTrue(_vcsIntegration.setProperty(testKey, testValue));
        assertEquals(testValue, _vcsIntegration.getProperty(testKey));
    }
}
