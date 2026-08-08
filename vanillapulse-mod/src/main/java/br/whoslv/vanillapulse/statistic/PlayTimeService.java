package br.whoslv.vanillapulse.statistic;

import br.whoslv.vanillapulse.event.handler.PlayerRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;

import java.util.*;

public class PlayTimeService {
    final Map<UUID, Integer> playersPlayTime = new HashMap<>();

    public void addPlayTimeByHour() {
        final Map<UUID, ServerPlayer> playersList = (new PlayerRegistry()).getAllPlayers();

        for (ServerPlayer player : playersList.values()) {
            int ticks = player.getStats()
                    .getValue(Stats.CUSTOM.get(Stats.PLAY_TIME));

            int horas = (ticks / 20) / 60 / 60;

            playersPlayTime.merge(player.getUUID(), horas, Integer::sum);
        }
    }

    public Map<UUID, Integer> getPlayTimeByHour() {
        addPlayTimeByHour();
        return playersPlayTime;
    }
}
