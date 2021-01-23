package io.github.nicholaslambell.gitprofiles.applications;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;

public class ApplicationManager {
    private final IApplication _application;

    public ApplicationManager(String[] args) {
        ApplicationMode applicationMode = detectApplicationMode(args);
        _application = initializeApplication(applicationMode, args);
    }

    public void run() {
        _application.run();
    }

    private static IApplication initializeApplication(ApplicationMode applicationMode, String[] args) {
        switch (applicationMode) {
            case TERMINAL:
                return new TerminalApplication(args);
            case INTERFACE:
                return new InterfaceApplication(args);
        }

        throw new IllegalArgumentException("Unable to initialize invalid ApplicationMode");
    }

    private static ApplicationMode detectApplicationMode(String[] args) {
        if (usingOverride(args)) {
            return detectOverride(args[0]);
        }

        return detectFromArgs(args);
    }

    private static boolean usingOverride(String[] args) {
        if (args.length == 0) {
            return false;
        }

        String override = args[0].toLowerCase();

        return (
            override.equals(ApplicationMode.TERMINAL.toLowerCase()) ||
            override.equals(ApplicationMode.INTERFACE.toLowerCase())
        );
    }

    private static ApplicationMode detectOverride(String override) {
        return ApplicationMode.parse(override);
    }

    private static ApplicationMode detectFromArgs(String[] args) {
        if (args.length == 0) {
            return ApplicationMode.INTERFACE;
        }

        return ApplicationMode.TERMINAL;
    }

    public ApplicationMode getApplicationMode() {
        return _application.getMode();
    }
}
