package io.github.nicholaslambell.gitprofiles.applications;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;

public class TerminalApplication implements IApplication {
    public TerminalApplication(String[] args) {

    }

    @Override
    public void run() {
    }

    @Override
    public ApplicationMode getMode() {
        return ApplicationMode.TERMINAL;
    }
}
