package io.github.nicholaslambell.gitprofiles.vcs;

import java.io.File;

public interface IVCSIntegration {
    File getDirectory();
    String getProperty(String key);
    String getLocalProperty(String key);
    boolean setProperty(String key, String value);
}
