package br.whoslv.vanillapulse.sync;

import br.whoslv.vanillapulse.VanillaPulse;
import br.whoslv.vanillapulse.database.entity.PlayerStatistics;
import br.whoslv.vanillapulse.statistic.StatisticType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.Map;
import java.util.UUID;

public class SyncManager {
    private static int ticks = 0;
    private static final int SYNC_INTERVAL = 6000; // 5 minutos
    private static int delayTicks = 0;

    public static void scheduleSync(MinecraftServer server) {
        delayTicks = 100;

        Map<UUID, Map<StatisticType, Integer>> stats = VanillaPulse.playerStatisticsRepository.getAllStatistics();

        VanillaPulse.statisticService.loadStatistics(stats);
    }

    public static void tick(MinecraftServer server) {
        if (delayTicks > 0) {
            delayTicks--;
            return;
        }

        ticks++;

        if (ticks >= SYNC_INTERVAL) {
            sync(server);
            ticks = 0;
        }
    }

    private static void sync(MinecraftServer server) {
        VanillaPulse.LOGGER.info("Sincronizando banco...");

        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            PlayerStatistics stats = createStats(player);

            VanillaPulse.LOGGER.info(
                    "Salvando {} | Blocos: {} | Mortes: {} | Kills: {}",
                    player.getName().getString(),
                    stats.getBlocksBroken(),
                    stats.getPlayerDeaths(),
                    stats.getPlayerKills()
            );

            VanillaPulse.playerStatisticsRepository.saveStatistics(stats);
            VanillaPulse.statisticService.clearStatistics(player.getUUID());
        }

        VanillaPulse.LOGGER.info("Banco sincronizado!");
    }

    private static PlayerStatistics createStats(ServerPlayer player) {
        UUID uuid = player.getUUID();
        Map<StatisticType,Integer> playerStats =VanillaPulse.statisticService.getStatistics(uuid);
        PlayerStatistics stats = new PlayerStatistics(uuid);

        stats.setBlocksBroken(playerStats.getOrDefault(StatisticType.BLOCK_BROKEN,0));
        stats.setPlayerDeaths(playerStats.getOrDefault(StatisticType.PLAYER_DEATH,0));
        stats.setPlayerKills(playerStats.getOrDefault(StatisticType.PLAYER_KILLED,0));
        stats.setMobKills(playerStats.getOrDefault(StatisticType.MOB_KILLED,0));
        stats.setDistanceTraveled(VanillaPulse.distanceService.getDistanceByKM().getOrDefault(uuid,0.0));
        stats.setPlayTimeHours(VanillaPulse.playTimeService.getPlayTimeByHour().getOrDefault(uuid,0));

        return stats;
    }
}
