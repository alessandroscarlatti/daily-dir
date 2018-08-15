package com.scarlatti.daily.dir.process;

import com.scarlatti.daily.dir.model.DailyDirProps;
import com.scarlatti.daily.dir.util.NewDirUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 8/13/2018
 */
public class AddToFavoritesProcessManager implements Runnable {

    private DailyDirProps props;
    private NewDirUtil newDirUtil;
    private Path newDir;
    private Path favoritesLink;

    public AddToFavoritesProcessManager(DailyDirProps props) {
        this.props = props;
        newDirUtil = new NewDirUtil(props);
    }

    @Override
    public void run() {
        // make a link to the new daily dir
        newDir = newDirUtil.newDirPath().toAbsolutePath();
        favoritesLink = Paths.get(props.getFavoritesDir(), newDir.getFileName().toString()).toAbsolutePath();

        try {
            if (!Files.exists(favoritesLink))
                Files.createSymbolicLink(favoritesLink, newDir);
        } catch (Exception e) {
            throw new RuntimeException("Error creating favorites link " + favoritesLink, e);
        }
    }

    private String mkLinkCommand() {
        return String.format("mklink /D %s %s", favoritesLink, newDir);
    }
}
