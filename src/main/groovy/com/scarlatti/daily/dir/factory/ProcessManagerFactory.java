package com.scarlatti.daily.dir.factory;

import com.scarlatti.daily.dir.model.DailyDirProps;
import com.scarlatti.daily.dir.process.AddToFavoritesProcessManager;
import com.scarlatti.daily.dir.process.CreateDailyDirProcessManager;
import com.scarlatti.daily.dir.process.RemoveFromFavoritesProcessManager;
import com.scarlatti.daily.dir.process.RemoveOldDailyDirsProcessManager;
import org.springframework.stereotype.Component;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 8/13/2018
 */
@Component
public class ProcessManagerFactory {

    private DailyDirProps props;

    public ProcessManagerFactory(DailyDirProps props) {
        this.props = props;
    }

    public CreateDailyDirProcessManager dailyDirProcessManager() {
        return new CreateDailyDirProcessManager(props);
    }

    public RemoveOldDailyDirsProcessManager removeOldDailyDirsProcessManager() {
        return new RemoveOldDailyDirsProcessManager(props);
    }

    public AddToFavoritesProcessManager addToFavoritesProcessManager() {
        return new AddToFavoritesProcessManager(props);
    }

    public RemoveFromFavoritesProcessManager removeFromFavoritesProcessManager() {
        return new RemoveFromFavoritesProcessManager(props);
    }
}

