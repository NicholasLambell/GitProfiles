package io.github.nicholaslambell.gitprofiles.vcs;

import java.io.File;

public interface IVCSIntegration {
    File getDirectory();
    String getProperty(String key);
    boolean setProperty(String key, String value);
    boolean propertyExists(String key);
    boolean isGlobalProperty(String key);
    boolean isLocalProperty(String key);
}
