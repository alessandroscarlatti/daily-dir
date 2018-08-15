package com.scarlatti.daily.dir.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileSystemUtils;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/14/2018
 */
public abstract class SimpleRemoveOldDirsProcessManager extends AbstractRemoveOldDirsProcessManager {

    private static final Logger log = LoggerFactory.getLogger(SimpleRemoveOldDirsProcessManager.class);

    protected abstract DateTimeFormatter formatter();

    @Override
    public void run() {
        log.info("Removing old daily dirs within {} days.", searchDays());

        // find all dirs within the period that match the format
        List<Path> matchingDirs = matchingDirs();

        for (Path dir : matchingDirs) {
            removeDir(dir);
        }
    }

    private List<Path> matchingDirs() {
        List<Path> paths = new ArrayList<>();

        try (DirectoryStream<Path> parentDir = Files.newDirectoryStream(searchDir())) {
            for (Path dir : parentDir) {
                if (dirQualifies(dir))
                    paths.add(dir);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error finding matching old dirs", e);
        }

        return paths;
    }

    protected abstract boolean dirQualifies(Path dir);

    protected boolean matchesPattern(Path path) {
        try {
            LocalDate.parse(path.getFileName().toString(), formatter());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isEmpty(Path dir) {
        try (Stream<Path> files = Files.list(dir)) {
            return (files.count() == 0);
        } catch (Exception e) {
            throw new RuntimeException("Error removing dir " + dir, e);
        }
    }

    /**
     * Negative values for remove from favorites days do work here...
     * They will never match "withinAge"
     *
     * @param path the path under consideration.
     * @return whether or not this path is within the age.
     */
    protected boolean withinAge(Path path) {
        LocalDate localDate = LocalDate.parse(path.getFileName().toString(), formatter());
        return
            localDate.isAfter(LocalDate.now().minus(searchDays() + 1, ChronoUnit.DAYS)) &&
                !localDate.equals(LocalDate.now());
    }

    private void removeDir(Path dir) {
        try {
            FileSystemUtils.deleteRecursively(dir);
        } catch (Exception e) {
            throw new RuntimeException("Error removing dir " + dir, e);
        }
    }
}
