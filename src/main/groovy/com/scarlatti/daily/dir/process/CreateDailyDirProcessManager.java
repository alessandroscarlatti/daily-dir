package com.scarlatti.daily.dir.process;

import com.scarlatti.daily.dir.model.DailyDirProps;
import com.scarlatti.daily.dir.util.NewDirUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 8/13/2018
 */
public class CreateDailyDirProcessManager implements Runnable {

    private DailyDirProps props;
    private NewDirUtil newDirUtil;
    private static final Logger log = LoggerFactory.getLogger(CreateDailyDirProcessManager.class);

    public CreateDailyDirProcessManager(DailyDirProps props) {
        this.props = props;
        newDirUtil = new NewDirUtil(props);
    }

    @Override
    public void run() {
        log.info("Creating the daily dir.");

        // create the daily dir from the format
        Path newDir = newDirUtil.newDirPath();
        try {
            Files.createDirectories(newDir);
        } catch (Exception e) {
            throw new RuntimeException("Error creating new directory " + newDir, e);
        }
    }
}
