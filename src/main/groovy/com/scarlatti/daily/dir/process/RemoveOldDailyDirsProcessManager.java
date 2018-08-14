package com.scarlatti.daily.dir.process;

import com.scarlatti.daily.dir.model.DailyDirProps;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 8/13/2018
 */
public class RemoveOldDailyDirsProcessManager implements Runnable {

    private DailyDirProps props;

    public RemoveOldDailyDirsProcessManager(DailyDirProps props) {
        this.props = props;
    }

    @Override
    public void run() {

    }
}
