package com.scarlatti.daily.dir.util;

import com.scarlatti.daily.dir.model.DailyDirProps;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/14/2018
 */
public class NewDirUtil {

    private DailyDirProps props;

    public NewDirUtil(DailyDirProps props) {
        this.props = props;
    }

    public Path newDirPath() {
        return Paths.get(props.getParentDir(), newDirName());
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
