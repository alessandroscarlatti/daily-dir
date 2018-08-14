package com.scarlatti.daily.dir.process;

import com.scarlatti.daily.dir.model.DailyDirProps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
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
 * Monday, 8/13/2018
 */
public class RemoveOldDailyDirsProcessManager implements Runnable {

    private DailyDirProps props;
    private DateTimeFormatter formatter;
    private static final Logger log = LoggerFactory.getLogger(RemoveOldDailyDirsProcessManager.class);

    public RemoveOldDailyDirsProcessManager(DailyDirProps props) {
        this.props = props;
        formatter = DateTimeFormatter.ofPattern(props.getDailyDirFormat());
    }

    @Override
    public void run() {
        log.info("Removing old daily dirs within {} days.", props.getRemoveOldDailyDirsDays());

        // find all dirs within the period that match the format
        List<Path> matchingDirs = matchingDirs();

        for (Path dir : matchingDirs) {
            removeDirIfEmpty(dir);
        }
    }

    private List<Path> matchingDirs() {
        List<Path> paths = new ArrayList<>();

        try (DirectoryStream<Path> parentDir = Files.newDirectoryStream(Paths.get(props.getParentDir()))) {
            for (Path dir : parentDir) {
                if (matchesPattern(dir) && withinAge(dir))
                    paths.add(dir);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error finding matching old dirs", e);
        }

        return paths;
    }

    private boolean matchesPattern(Path path) {
        try {
            LocalDate.parse(path.getFileName().toString(), formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Negative values for remove from favorites days do work here...
     * They will never match "withinAge"
     * @param path the path under consideration.
     * @return whether or not this path is within the age.
     */
    private boolean withinAge(Path path) {
        try {
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            return (attr.creationTime().toInstant().plus(props.getRemoveFromFavoritesDays(), ChronoUnit.DAYS).isAfter(Instant.now()));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file attributes for " + path, e);
        }
    }

    private static void removeDirIfEmpty(Path dir) {
        try (Stream<Path> files = Files.list(dir)) {
            if (files.count() > 0)
                FileSystemUtils.deleteRecursively(dir);
        } catch (Exception e) {
            throw new RuntimeException("Error removing dir " + dir, e);
        }
    }
}
