package io.github.nicholaslambell.gitprofiles.commands;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;
import io.github.nicholaslambell.gitprofiles.utils.PathUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PrimaryCommandTest extends CommandTestBase {

    private PrimaryCommand commandWithArgs(String... args) {
        return new PrimaryCommand(args);
    }

    private void assertApplicationMode(String optionKey, ApplicationMode mode) {
        PrimaryCommand command = commandWithArgs(optionKey, mode.toLowerCase());

        assertEquals(mode, command.getMode());
    }

    @Test
    public void testModeTerminalLong() {
        assertApplicationMode("--mode", ApplicationMode.TERMINAL);

        assertCommandSuccess();
    }

    @Test
    public void testModeTerminalShort() {
        assertApplicationMode("-m", ApplicationMode.TERMINAL);

        assertCommandSuccess();
    }

    @Test
    public void testModeTerminalImplicit() {
        PrimaryCommand command = commandWithArgs("argument");

        assertCommandSuccess();
        assertEquals(ApplicationMode.TERMINAL, command.getMode());
    }

    @Test
    public void testModeInterfaceLong() {
        assertApplicationMode("--mode", ApplicationMode.INTERFACE);

        assertCommandSuccess();
    }

    @Test
    public void testModeInterfaceShort() {
        assertApplicationMode("-m", ApplicationMode.INTERFACE);

        assertCommandSuccess();
    }

    @Test
    public void testModeInvalid() {
        PrimaryCommand command = commandWithArgs("--mode", "example");

        assertCommandError();
        assertNull(command.getMode());
    }

    @Test
    public void testModeInterfaceImplicit() {
        PrimaryCommand command = commandWithArgs();

        assertCommandSuccess();
        assertEquals(ApplicationMode.INTERFACE, command.getMode());
    }

    @Test
    public void testImplicitDirectory() {
        PrimaryCommand command = commandWithArgs();

        assertCommandSuccess();
        assertEquals(PathUtils.workingDirectory().toFile(), command.getDirectory());
    }

    @Test
    public void testExplicitDirectoryLong() {
        Path workingDirectory = Paths.get(PathUtils.workingDirectory().toString(), "testing");
        PrimaryCommand command = commandWithArgs("--directory", workingDirectory.toString());

        assertCommandSuccess();
        assertEquals(workingDirectory.toFile(), command.getDirectory());
    }

    @Test
    public void testExplicitDirectoryShort() {
        Path workingDirectory = Paths.get(PathUtils.workingDirectory().toString(), "testing");
        PrimaryCommand command = commandWithArgs("-d", workingDirectory.toString());

        assertCommandSuccess();
        assertEquals(workingDirectory.toFile(), command.getDirectory());
    }

    @Test
    public void testDirectoryInvalid() {
        PrimaryCommand command = commandWithArgs("--directory", null);

        assertCommandError();
        assertNull(command.getDirectory());
    }

    @Test
    public void testUnmatchedArgsEmpty() {
        PrimaryCommand command = commandWithArgs("--mode", ApplicationMode.TERMINAL.toLowerCase());

        assertCommandSuccess();
        assertEquals(0, command.getUnmatchedArgs().length);
    }

    @Test
    public void testUnmatchedArgsNonEmpty() {
        PrimaryCommand command = commandWithArgs("--mode", ApplicationMode.TERMINAL.toLowerCase(), "unmatched");

        assertCommandSuccess();
        assertEquals(1, command.getUnmatchedArgs().length);
    }
}
