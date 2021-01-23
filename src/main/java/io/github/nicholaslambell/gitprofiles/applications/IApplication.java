package io.github.nicholaslambell.gitprofiles.applications;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;

public interface IApplication {
    void run();
    ApplicationMode getMode();
}
