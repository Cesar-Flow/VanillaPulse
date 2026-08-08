package br.whoslv.vanillapulse.statistic;

import br.whoslv.vanillapulse.event.handler.PlayerRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DistanceService {
    final Map<UUID, Double> playersDistance = new HashMap<>();

    public void addDistanceByKM() {
        final Map<UUID, ServerPlayer> playersList = (new PlayerRegistry()).getAllPlayers();

        for (ServerPlayer player : playersList.values()) {
            int walk = player.getStats()
                    .getValue(Stats.CUSTOM.get(Stats.WALK_ONE_CM));

            double km = walk / 100000.0;

            playersDistance.merge(player.getUUID(), km, Double::sum);
        }
    }

    public Map<UUID, Double> getDistanceByKM() {
        addDistanceByKM();
        return playersDistance;
    }
}
