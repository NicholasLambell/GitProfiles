package io.github.nicholaslambell.gitprofiles.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtils {
    public static Path workingDirectory() {
        return Paths.get(System.getProperty("user.dir"));
    }
}
