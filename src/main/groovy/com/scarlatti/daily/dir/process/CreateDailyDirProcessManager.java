package com.scarlatti.daily.dir.process;

import com.scarlatti.daily.dir.model.DailyDirProps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 8/13/2018
 */
public class CreateDailyDirProcessManager implements Runnable {

    private DailyDirProps props;
    private static final Logger log = LoggerFactory.getLogger(CreateDailyDirProcessManager.class);

    public CreateDailyDirProcessManager(DailyDirProps props) {
        this.props = props;
    }

    @Override
    public void run() {
        log.info("Creating new daily dir.");

        // create the daily dir from the format
        Path newDir = Paths.get(props.getParentDir(), newDirName());
        try {
            Files.createDirectories(newDir);
        } catch (Exception e) {
            throw new RuntimeException("Error creating new directory " + newDir, e);
        }
    }

    private String newDirName() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(props.getDailyDirFormat());
            return formatter.format(LocalDate.now());
        } catch (Exception e) {
            throw new RuntimeException("Error creating directory name from format [" + props.getDailyDirFormat() + "]", e);
        }
    }
}
