package com.scarlatti.daily.dir.schedule;

import com.scarlatti.daily.dir.model.DailyDirProps;
import com.scarlatti.daily.dir.service.DailyDirService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private DailyDirService processManager;
    private static final Logger log = LoggerFactory.getLogger(DailyDirScheduler.class);

    public DailyDirScheduler(DailyDirProps props, DailyDirService processManager) {
        this.props = props;
        this.processManager = processManager;
    }

    @Override
    public void run(String... args) throws Exception {
        if (props.getRunOnStartup()) {
            invokeDailyDirProcess();
        }
    }

    @Scheduled(cron = "${daily-dir.cron}")
    public void invokeDailyDirProcess() {
        try {
            processManager.executeDailyDirProcess();
        } catch (Exception e) {
            log.error("Error excuting daily dir process.", e);
        }
    }
}
