package br.whoslv.vanillapulse.statistic;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatisticService {
    private static final Map<UUID, Map<StatisticType, Integer>> statistics = new HashMap<>();

    public void addStatistic(Player player, StatisticType type) {
        statistics.computeIfAbsent(player.getUUID(), uuid -> new HashMap<>())
                .merge(type, 1, Integer::sum);
    }

    public void addStatistic(Player player, StatisticType type, int amount) {
        statistics.computeIfAbsent(player.getUUID(), uuid -> new HashMap<>())
                .merge(type, amount, Integer::sum);
    }

    public int countStatistic(Player player, StatisticType type) {
        return statistics
                .getOrDefault(player.getUUID(), Map.of())
                .getOrDefault(type, 0);
    }

    public void loadStatistics(Map<UUID, Map<StatisticType, Integer>> loadedStatistics) {
        statistics.clear();
        statistics.putAll(loadedStatistics);
    }

    public Map<StatisticType, Integer> getStatistics(UUID uuid) {
        return statistics.getOrDefault(uuid, new HashMap<>());
    }

    public void clearStatistics(UUID uuid) {
        statistics.remove(uuid);
    }
}
