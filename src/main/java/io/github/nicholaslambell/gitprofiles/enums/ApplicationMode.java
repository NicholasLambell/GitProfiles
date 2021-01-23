package io.github.nicholaslambell.gitprofiles.enums;

public enum ApplicationMode {
    TERMINAL,
    INTERFACE;

    public static ApplicationMode parse(String value) {
        return valueOf(value.toUpperCase());
    }

    public String toLowerCase() {
        return name().toLowerCase();
    }
}
