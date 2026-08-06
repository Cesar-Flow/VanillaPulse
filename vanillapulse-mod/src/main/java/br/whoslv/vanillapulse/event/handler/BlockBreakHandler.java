package br.whoslv.vanillapulse.event.handler;

import br.whoslv.vanillapulse.VanillaPulse;
import br.whoslv.vanillapulse.statistic.StatisticService;
import br.whoslv.vanillapulse.statistic.StatisticType;
import net.minecraft.world.entity.player.Player;

public class BlockBreakHandler {
    public void handle(Player player) { handle(player, 1); }

    public static void handle(Player player, int amount) {
        StatisticService statisticService = new StatisticService();

        statisticService.addStatistic(
                player,
                StatisticType.BLOCK_BROKEN,
                amount
        );

        VanillaPulse.LOGGER.info(
                String.format(
                        "%s quebrou %d bloco(s)",
                        player.getName().getString(),
                        statisticService.countStatistic(player, StatisticType.BLOCK_BROKEN)
                )
        );

    }
}
