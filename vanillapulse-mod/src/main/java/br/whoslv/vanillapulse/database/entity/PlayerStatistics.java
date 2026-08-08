package br.whoslv.vanillapulse.database.entity;

import java.util.UUID;

public class PlayerStatistics {
    private final UUID uuid;
    private int blocksBroken = 0;
    private int playerDeaths = 0;
    private int playerKills = 0;
    private int mobKills = 0;
    private double distanceTraveled = 0;
    private int playTimeHours = 0;

    public PlayerStatistics(UUID uuid) {
        this.uuid = uuid;
    }

    public PlayerStatistics(UUID uuid, int blocksBroken, int playerDeaths, int playerKills, int mobKills, double distanceTraveled, int playTimeHours) {
        this.uuid = uuid;
        this.blocksBroken = blocksBroken;
        this.playerDeaths = playerDeaths;
        this.playerKills = playerKills;
        this.mobKills = mobKills;
        this.distanceTraveled = distanceTraveled;
        this.playTimeHours = playTimeHours;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getBlocksBroken() {
        return blocksBroken;
    }

    public int getPlayerDeaths() {
        return playerDeaths;
    }

    public int getPlayerKills() {
        return playerKills;
    }

    public int getMobKills() {
        return mobKills;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public int getPlayTimeHours() {
        return playTimeHours;
    }

    public void setBlocksBroken(int blocksBroken) {
        this.blocksBroken = blocksBroken;
    }

    public void setPlayerDeaths(int playerDeaths) {
        this.playerDeaths = playerDeaths;
    }

    public void setPlayerKills(int playerKills) {
        this.playerKills = playerKills;
    }

    public void setMobKills(int mobKills) {
        this.mobKills = mobKills;
    }

    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public void setPlayTimeHours(int playTimeHours) {
        this.playTimeHours = playTimeHours;
    }
}