package br.whoslv.vanillapulse.event.handler;

import br.whoslv.vanillapulse.VanillaPulse;
import br.whoslv.vanillapulse.statistic.StatisticService;
import br.whoslv.vanillapulse.statistic.StatisticType;
import net.minecraft.world.entity.player.Player;

public class PlayerKillHandler {
    private final StatisticService statisticService;

    public PlayerKillHandler() {
        this.statisticService = new StatisticService();
    }

    public void handle(Player killer, Player player) {
        this.statisticService.addStatistic(player, StatisticType.PLAYER_KILLED);

        VanillaPulse.LOGGER.info(String.format("%s matou %d player(s)", player.getName().getString(),
                statisticService.countStatistic(player, StatisticType.PLAYER_KILLED)));
    }
}
