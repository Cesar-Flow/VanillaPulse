package br.whoslv.vanillapulse.statistic;

import br.whoslv.vanillapulse.event.handler.PlayerRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;

import java.util.*;

public class PlayTimeService {
    public Map<UUID, Map<ServerPlayer, Long>> getPlayTimeByHour() {
        final Map<UUID, ServerPlayer> playersList = (new PlayerRegistry()).getAllPlayers();
        final Map<UUID, Map<ServerPlayer, Long>> playersPlayTime = new HashMap<>();

        for (ServerPlayer player : playersList.values()) {
            int ticks = player.getStats()
                    .getValue(Stats.CUSTOM.get(Stats.PLAY_TIME));

            long horas = (ticks / 20L) / 60 / 60;

            playersPlayTime.computeIfAbsent(player.getUUID(), k -> new HashMap<>())
                    .put(player, horas);
        }

        return playersPlayTime;
    }
}
