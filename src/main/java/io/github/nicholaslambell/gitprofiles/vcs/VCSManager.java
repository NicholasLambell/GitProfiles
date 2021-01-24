package io.github.nicholaslambell.gitprofiles.vcs;

import java.io.File;
import java.nio.file.Path;

public class VCSManager {
    private final IVCSIntegration _vcsIntegration;

    public VCSManager(IVCSIntegration vcsIntegration) {
        _vcsIntegration = vcsIntegration;
    }

    public File getDirectory() {
        return _vcsIntegration.getDirectory();
    }

    public Path getPath() {
        return _vcsIntegration.getDirectory().toPath();
    }

    public String getProperty(String key) {
        return _vcsIntegration.getProperty(key);
    }

    public boolean setProperty(String key, String value) {
        return _vcsIntegration.setProperty(key, value);
    }

    public boolean isPropertyOverridden(String key) {
        return _vcsIntegration.getLocalProperty(key) != null;
    }
}
