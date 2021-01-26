package io.github.nicholaslambell.gitprofiles.applications;

import io.github.nicholaslambell.gitprofiles.commands.PrimaryCommand;
import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;

import java.io.File;

public class ApplicationManager {
    private final IApplication _application;

    public ApplicationManager(String[] args) {
        PrimaryCommand command = new PrimaryCommand(args);

        _application = initializeApplication(
            command.getMode(),
            command.getDirectory(),
            command.getUnmatchedArgs()
        );
    }

    public void run() {
        _application.run();
    }

    private static IApplication initializeApplication(ApplicationMode applicationMode, File directory, String[] args) {
        try {
            switch (applicationMode) {
                case TERMINAL:
                    return new TerminalApplication(directory, args);
                case INTERFACE:
                    return new InterfaceApplication(directory, args);
            }
        } catch (Exception e) {
            // todo: Actual error handling
            System.out.printf("Error encountered while initializing application: %s%n", e);
        }

        throw new IllegalArgumentException("Unable to initialize invalid ApplicationMode");
    }

    public ApplicationMode getApplicationMode() {
        return _application.getMode();
    }
}
