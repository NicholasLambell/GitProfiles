package io.github.nicholaslambell.gitprofiles.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class CommandTestBase {
    protected final PrintStream originalOut = System.out;
    protected final PrintStream originalErr = System.err;
    protected final ByteArrayOutputStream out = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream err = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        out.reset();
        err.reset();

        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    protected void assertCommandSuccess() {
        assertEquals("", err.toString());
    }

    protected void assertCommandError() {
        assertNotEquals("", err.toString());
    }
}
