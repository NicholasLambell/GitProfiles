package io.github.nicholaslambell.gitprofiles.applications;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;

import java.io.File;

public class TerminalApplication implements IApplication {
    public TerminalApplication(File directory, String[] args) {

    }

    @Override
    public void run() {

    }

    @Override
    public ApplicationMode getMode() {
        return ApplicationMode.TERMINAL;
    }
}
