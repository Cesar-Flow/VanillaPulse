package br.whoslv.vanillapulse.event.handler;

import br.whoslv.vanillapulse.VanillaPulse;
import br.whoslv.vanillapulse.statistic.StatisticService;
import br.whoslv.vanillapulse.statistic.StatisticType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class MobKillHandler {
    private final StatisticService statisticService;

    public MobKillHandler() {
        this.statisticService = new StatisticService();
    }

    public void handle(Player player, LivingEntity mob) {
        this.statisticService.addStatistic(player, StatisticType.MOB_KILLED);

        VanillaPulse.LOGGER.info(String.format("%s matou %d mob(s)", player.getName().getString(),
                statisticService.countStatistic(player, StatisticType.MOB_KILLED)));
    }
}
