package br.whoslv.vanillapulse.statistic;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatisticService {
    private static final Map<UUID, Integer> blocksBroken = new HashMap<>();

    public void addBlockBroken(Player player, String block) {
        blocksBroken.merge(player.getUUID(), 1, Integer::sum);
    }

    public int countBlockBroken(UUID playerUUID) {
        return blocksBroken.getOrDefault(playerUUID, 0);
    }
}
