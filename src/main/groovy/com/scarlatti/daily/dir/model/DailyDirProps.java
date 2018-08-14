package com.scarlatti.daily.dir.model;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 8/13/2018
 */
public class DailyDirProps {
    private boolean runOnStartup;
    private boolean addToFavorites;
    private boolean removeFromFavorites;
    private int removeFromFavoritesDays;
    private boolean createDailyDir;
    private boolean removeOldDailyDirs;
    private int removeOldDailyDirsDays;
    private boolean runCron;
    private String cron;
    private String parentDir;
    private String favoritesDir;

    public boolean getRunOnStartup() {
        return runOnStartup;
    }

    public void setRunOnStartup(boolean runOnStartup) {
        this.runOnStartup = runOnStartup;
    }

    public boolean getAddToFavorites() {
        return addToFavorites;
    }

    public void setAddToFavorites(boolean addToFavorites) {
        this.addToFavorites = addToFavorites;
    }

    public boolean getRemoveFromFavorites() {
        return removeFromFavorites;
    }

    public void setRemoveFromFavorites(boolean removeFromFavorites) {
        this.removeFromFavorites = removeFromFavorites;
    }

    public int getRemoveFromFavoritesDays() {
        return removeFromFavoritesDays;
    }

    public void setRemoveFromFavoritesDays(int removeFromFavoritesDays) {
        this.removeFromFavoritesDays = removeFromFavoritesDays;
    }

    public boolean getCreateDailyDir() {
        return createDailyDir;
    }

    public void setCreateDailyDir(boolean createDailyDir) {
        this.createDailyDir = createDailyDir;
    }

    public boolean getRemoveOldDailyDirs() {
        return removeOldDailyDirs;
    }

    public void setRemoveOldDailyDirs(boolean removeOldDailyDirs) {
        this.removeOldDailyDirs = removeOldDailyDirs;
    }

    public int getRemoveOldDailyDirsDays() {
        return removeOldDailyDirsDays;
    }

    public void setRemoveOldDailyDirsDays(int removeOldDailyDirsDays) {
        this.removeOldDailyDirsDays = removeOldDailyDirsDays;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public boolean getRunCron() {
        return runCron;
    }

    public void setRunCron(boolean runCron) {
        this.runCron = runCron;
    }

    public String getParentDir() {
        return parentDir;
    }

    public void setParentDir(String parentDir) {
        this.parentDir = parentDir;
    }

    public String getFavoritesDir() {
        return favoritesDir;
    }

    public void setFavoritesDir(String favoritesDir) {
        this.favoritesDir = favoritesDir;
    }
}
