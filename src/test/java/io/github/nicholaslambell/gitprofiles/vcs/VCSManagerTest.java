package io.github.nicholaslambell.gitprofiles.vcs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VCSManagerTest {
    @TempDir
    File testDirectory;

    private final String _testPropertyKey = "user.name";
    private final String _testPropertyValue = "Example";

    // Testing Class
    private VCSManager _vcsManager;

    // Dependencies
    private IVCSIntegration _vcsIntegration;

    @BeforeEach
    public void setUp() {
        _vcsIntegration = mock(IVCSIntegration.class);
        _vcsManager = new VCSManager(_vcsIntegration);
    }

    @Test
    public void testGetDirectory() {
        when(_vcsIntegration.getDirectory()).thenReturn(testDirectory);

        assertEquals(testDirectory, _vcsManager.getDirectory());
    }

    @Test
    public void testGetPath() {
        when(_vcsIntegration.getDirectory()).thenReturn(testDirectory);

        assertEquals(testDirectory.toPath(), _vcsManager.getPath());
    }

    @Test
    public void testPropertyExistsTrue() {
        when(_vcsIntegration.propertyExists(_testPropertyKey)).thenReturn(true);

        assertTrue(_vcsManager.propertyExists(_testPropertyKey));
    }

    @Test
    public void testPropertyExistsFalse() {
        when(_vcsIntegration.propertyExists(_testPropertyKey)).thenReturn(false);

        assertFalse(_vcsManager.propertyExists(_testPropertyKey));
    }

    @Test
    public void testGetProperty() {

        when(_vcsIntegration.getProperty(_testPropertyKey)).thenReturn(_testPropertyValue);

        assertEquals(_testPropertyValue, _vcsManager.getProperty(_testPropertyKey));
    }

    @Test
    public void testSetProperty() {
        when(_vcsIntegration.setProperty(_testPropertyKey, _testPropertyValue)).thenReturn(true);

        assertTrue(_vcsManager.setProperty(_testPropertyKey, _testPropertyValue));
    }

    @Test
    public void testIsPropertyOverriddenTrue() {
        when(_vcsIntegration.isLocalProperty(_testPropertyKey)).thenReturn(true);

        assertTrue(_vcsManager.isPropertyOverridden(_testPropertyKey));
    }

    @Test
    public void testIsPropertyOverriddenFalse() {
        when(_vcsIntegration.isLocalProperty(_testPropertyKey)).thenReturn(false);

        assertFalse(_vcsManager.isPropertyOverridden(_testPropertyKey));
    }
}
