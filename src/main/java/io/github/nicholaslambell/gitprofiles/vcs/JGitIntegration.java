package io.github.nicholaslambell.gitprofiles.vcs;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class JGitIntegration implements IVCSIntegration {
    private final Repository _repository;
    private final StoredConfig _configuration;

    public JGitIntegration(File directory) throws IOException {
        _repository = new FileRepositoryBuilder()
            .setGitDir(directory)
            .build();

        _configuration = _repository.getConfig();
    }

    @Override
    public File getDirectory() {
        return _repository.getDirectory();
    }

    @Override
    public String getProperty(String key) {
        String[] keyParts = splitKey(key);

        return _configuration.getString(keyParts[0], null, keyParts[1]);
    }

    @Override
    public boolean setProperty(String key, String value) {
        String[] keyParts = splitKey(key);

        _configuration.setString(keyParts[0], null, keyParts[1], value);

        try {
            _configuration.save();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean propertyExists(String key) {
        return getProperty(key) != null;
    }

    @Override
    public boolean isGlobalProperty(String key) {
        return (
            propertyExists(key) &&
            !isLocalProperty(key)
        );
    }

    @Override
    public boolean isLocalProperty(String key) {
        String[] keyParts = splitKey(key);

        Set<String> sectionKeys = _configuration.getNames(keyParts[0]);

        return sectionKeys.contains(keyParts[1]);
    }

    private String[] splitKey(String key) {
        String[] keyParts = key.split("\\.");

        if (keyParts.length < 2) {
            throw new IllegalArgumentException("Invalid Key Provided. Must have at least two parts");
        }

        return keyParts;
    }
}
