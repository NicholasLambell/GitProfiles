package io.github.nicholaslambell.gitprofiles.applications;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;

import java.io.File;

public class InterfaceApplication implements IApplication {
    public InterfaceApplication(File directory, String[] args) {

    }

    @Override
    public void run() {
    }

    @Override
    public ApplicationMode getMode() {
        return ApplicationMode.INTERFACE;
    }
}
