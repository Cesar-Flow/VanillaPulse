package br.whoslv.vanillapulse.statistic;

import br.whoslv.vanillapulse.event.handler.PlayerRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DistanceService {
    public Map<UUID, Map<ServerPlayer, Double>> getDistanceByKM() {
        final Map<UUID, ServerPlayer> playersList = (new PlayerRegistry()).getAllPlayers();
        final Map<UUID, Map<ServerPlayer, Double>> playersDistance = new HashMap<>();

        for (ServerPlayer player : playersList.values()) {
            int walk = player.getStats()
                    .getValue(Stats.CUSTOM.get(Stats.WALK_ONE_CM));

            double km = walk / 100000.0;

            playersDistance.computeIfAbsent(player.getUUID(), k -> new HashMap<>())
                    .put(player, km);
        }

        return playersDistance;
    }
}
