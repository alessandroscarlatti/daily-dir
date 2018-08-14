package com.scarlatti.daily.dir;

import com.scarlatti.daily.dir.model.DailyDirProps;
import com.scarlatti.daily.dir.process.DailyDirProcessManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 8/13/2018
 */
@Component
public class DailyDirScheduler implements CommandLineRunner {

    private DailyDirProps props;
    private DailyDirProcessManager processManager;

    public DailyDirScheduler(DailyDirProps props, DailyDirProcessManager processManager) {
        this.props = props;
        this.processManager = processManager;
    }

    @Override
    public void run(String... args) throws Exception {
        if (props.getRunOnStartup()) {
            invokeDailyDirProcess();
        }
    }

    @Scheduled(cron = "${dailyDir.cron}")
    public void invokeDailyDirProcess() {
        processManager.executeDailyDirProcess();
    }
}
