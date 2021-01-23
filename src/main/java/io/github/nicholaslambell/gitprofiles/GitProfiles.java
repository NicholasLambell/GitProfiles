package io.github.nicholaslambell.gitprofiles;

import io.github.nicholaslambell.gitprofiles.applications.ApplicationManager;

public class GitProfiles {
    public static void main(String[] args) {
        ApplicationManager applicationManager = new ApplicationManager(args);
        applicationManager.run();
    }
}
