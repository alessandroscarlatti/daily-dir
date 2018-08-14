package com.scarlatti.daily.dir.config;

import com.scarlatti.daily.dir.model.DailyDirProps;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 8/13/2018
 */
@Configuration
@EnableScheduling
public class Config {

    @Bean
    @ConfigurationProperties("daily-dir")
    public DailyDirProps dailyDirProps() {
        return defaultDailyDirProps();
    }

    private DailyDirProps defaultDailyDirProps() {
        DailyDirProps props = new DailyDirProps();
        props.setRunOnStartup(true);
        props.setAddToFavorites(true);
        props.setRemoveFromFavorites(true);
        props.setRemoveFromFavoritesDays(3);
        props.setCreateDailyDir(true);
        props.setDailyDirFormat("M-dd-yyyy");
        props.setRemoveOldDailyDirs(true);
        props.setRemoveOldDailyDirsDays(3);
        props.setRunCron(true);
        props.setCron("* * * * * *");

        return props;
    }
}
