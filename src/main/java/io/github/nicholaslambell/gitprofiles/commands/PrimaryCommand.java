package io.github.nicholaslambell.gitprofiles.commands;

import io.github.nicholaslambell.gitprofiles.enums.ApplicationMode;
import io.github.nicholaslambell.gitprofiles.utils.PathUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Unmatched;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Command(name = "git-profiles")
public class PrimaryCommand implements Runnable {
    @Option(names = { "-m", "--mode" }, description = "Force application mode; terminal, interface")
    private ApplicationMode mode;

    @Option(names = { "-d", "--directory" }, description = "Target a directory other than current working path")
    private File directory;

    @Unmatched
    private List<String> unmatched;

    public PrimaryCommand(String[] args) {
        new CommandLine(this)
            .setCaseInsensitiveEnumValuesAllowed(true)
            .execute(args);
    }

    @Override
    public void run() {
        initializeUnmatched();

        initializeMode();
        initializeDirectory();
    }

    private void initializeUnmatched() {
        if (unmatched == null) {
            unmatched = new ArrayList<>();
        }
    }

    private void initializeMode() {
        if (mode != null) {
            return;
        }

        mode = (unmatched.size() == 0 ? ApplicationMode.INTERFACE : ApplicationMode.TERMINAL);
    }

    private void initializeDirectory() {
        if (directory == null) {
            directory = PathUtils.workingDirectory().toFile();
        }
    }

    public ApplicationMode getMode() {
        return mode;
    }

    public File getDirectory() {
        return directory;
    }

    public String[] getUnmatchedArgs() {
        return unmatched.toArray(new String[0]);
    }
}
