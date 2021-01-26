package io.github.nicholaslambell.gitprofiles.utils;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathUtilsTest {
    @Test
    public void testWorkingDirectory() {
        Path path = PathUtils.workingDirectory();
        Path pathManual = Paths.get(System.getProperty("user.dir"));

        assertEquals(pathManual, path);
    }
}
