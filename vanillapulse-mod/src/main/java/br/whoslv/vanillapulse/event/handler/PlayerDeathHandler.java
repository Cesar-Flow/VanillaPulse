package br.whoslv.vanillapulse.event.handler;

import br.whoslv.vanillapulse.VanillaPulse;
import br.whoslv.vanillapulse.statistic.StatisticService;
import br.whoslv.vanillapulse.statistic.StatisticType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public class PlayerDeathHandler {
    private final StatisticService statisticService;

    public PlayerDeathHandler() {
        statisticService = new StatisticService();
    }

    public void handle(Player player, DamageSource damageSource) {
        statisticService.addStatistic(player, StatisticType.PLAYER_DEATH);

        VanillaPulse.LOGGER.info(String.format("%s morreu %d vez(es)", player.getName().getString(),
                statisticService.countStatistic(player, StatisticType.PLAYER_DEATH)));
    }
}
