package com.scarlatti.daily.dir.process;

import com.scarlatti.daily.dir.model.DailyDirProps;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 8/13/2018
 */
public class RemoveFromFavoritesProcessManager extends SimpleRemoveOldDirsProcessManager {

    private DailyDirProps props;
    private DateTimeFormatter formatter;

    public RemoveFromFavoritesProcessManager(DailyDirProps props) {
        this.props = props;
        formatter = DateTimeFormatter.ofPattern(props.getDailyDirFormat());
    }

    @Override
    protected boolean dirQualifies(Path dir) {
        return matchesPattern(dir) &&
            withinAge(dir);
    }


    @Override
    protected DateTimeFormatter formatter() {
        return formatter;
    }

    @Override
    protected Path searchDir() {
        return Paths.get(props.getFavoritesDir());
    }

    @Override
    protected int searchDays() {
        return props.getRemoveFromFavoritesDays();
    }
}
