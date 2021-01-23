package io.github.nicholaslambell.gitprofiles.applications;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;

public class InterfaceApplication implements IApplication {
    public InterfaceApplication(String[] args) {

    }

    @Override
    public void run() {
    }

    @Override
    public ApplicationMode getMode() {
        return ApplicationMode.INTERFACE;
    }
}
