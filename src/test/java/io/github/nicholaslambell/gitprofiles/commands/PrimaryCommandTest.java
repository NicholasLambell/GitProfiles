package io.github.nicholaslambell.gitprofiles.commands;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;
import io.github.nicholaslambell.gitprofiles.utils.PathUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimaryCommandTest {

    private PrimaryCommand commandWithOption(String optionKey, String optionValue) {
        String[] args = { optionKey, optionValue };
        return new PrimaryCommand(args);
    }

    private void assertApplicationMode(String optionKey, ApplicationMode mode) {
        PrimaryCommand command = commandWithOption(optionKey, mode.toLowerCase());

        assertEquals(mode, command.getMode());
    }

    @Test
    public void testModeTerminalLong() {
        assertApplicationMode("--mode", ApplicationMode.TERMINAL);
    }

    @Test
    public void testModeTerminalShort() {
        assertApplicationMode("-m", ApplicationMode.TERMINAL);
    }

    @Test
    public void testModeTerminalImplicit() {
        String[] args = { "argument" };
        PrimaryCommand command = new PrimaryCommand(args);

        assertEquals(ApplicationMode.TERMINAL, command.getMode());
    }

    @Test
    public void testModeInterfaceLong() {
        assertApplicationMode("--mode", ApplicationMode.INTERFACE);
    }

    @Test
    public void testModeInterfaceShort() {
        assertApplicationMode("-m", ApplicationMode.INTERFACE);
    }

    @Test
    public void testModeInterfaceImplicit() {
        String[] args = {};
        PrimaryCommand command = new PrimaryCommand(args);

        assertEquals(ApplicationMode.INTERFACE, command.getMode());
    }

    @Test
    public void testImplicitDirectory() {
        String[] args = {};
        PrimaryCommand command = new PrimaryCommand(args);

        assertEquals(PathUtils.workingDirectory().toFile(), command.getDirectory());
    }

    @Test
    public void testExplicitDirectoryLong() {
        Path workingDirectory = Paths.get(PathUtils.workingDirectory().toString(), "testing");
        PrimaryCommand command = commandWithOption("--directory", workingDirectory.toString());

        assertEquals(workingDirectory.toFile(), command.getDirectory());
    }

    @Test
    public void testExplicitDirectoryShort() {
        Path workingDirectory = Paths.get(PathUtils.workingDirectory().toString(), "testing");
        PrimaryCommand command = commandWithOption("-d", workingDirectory.toString());

        assertEquals(workingDirectory.toFile(), command.getDirectory());
    }

    @Test
    public void testUnmatchedArgsEmpty() {
        String[] args = { "--mode", ApplicationMode.TERMINAL.toLowerCase() };
        PrimaryCommand command = new PrimaryCommand(args);

        assertEquals(0, command.getUnmatchedArgs().length);
    }

    @Test
    public void testUnmatchedArgsNonEmpty() {
        String[] args = { "--mode", ApplicationMode.TERMINAL.toLowerCase(), "unmatched" };
        PrimaryCommand command = new PrimaryCommand(args);

        assertEquals(1, command.getUnmatchedArgs().length);
    }
}
