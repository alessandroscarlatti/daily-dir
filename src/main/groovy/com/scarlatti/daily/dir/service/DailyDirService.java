package com.scarlatti.daily.dir.service;

import com.scarlatti.daily.dir.factory.ProcessManagerFactory;
import com.scarlatti.daily.dir.model.DailyDirProps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 8/13/2018
 */
@Component
public class DailyDirService {

    private ProcessManagerFactory factory;
    private DailyDirProps props;
    private static final Logger log = LoggerFactory.getLogger(DailyDirService.class);

    public DailyDirService(ProcessManagerFactory factory, DailyDirProps props) {
        this.factory = factory;
        this.props = props;
    }

    /**
     * Perform the daily dir process.
     * This includes:
     *
     * - create the new daily dir
     * - remove the old daily dir from the favorites dir (by pattern) (if exists)
     * - add the new daily dir to the favorites dir
     * - remove any previous daily dirs that are empty (within a configurable timespan)
     */
    public void executeDailyDirProcess() {
        log.info("Executing DailyDir process with props: " + props);

        if (props.getCreateDailyDir())
            createDailyDir();

        if (props.getRemoveOldDailyDirs())
            removeOldDailyDirs();

        if (props.getAddToFavorites())
            addToFavorites();

        if (props.getRemoveFromFavorites())
            removeOldFavorites();

        log.info("Daily dir process complete.");
    }

    public void createDailyDir() {
        factory.dailyDirProcessManager().run();
    }

    public void removeOldDailyDirs() {
        factory.removeOldDailyDirsProcessManager().run();
    }

    public void addToFavorites() {
        factory.addToFavoritesProcessManager().run();
    }

    public void removeOldFavorites() {
        factory.removeFromFavoritesProcessManager().run();
    }
}
