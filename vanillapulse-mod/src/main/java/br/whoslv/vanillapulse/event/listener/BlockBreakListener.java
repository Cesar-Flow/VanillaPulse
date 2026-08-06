package br.whoslv.vanillapulse.event.listener;

import br.whoslv.vanillapulse.VanillaPulse;
import br.whoslv.vanillapulse.event.handler.BlockBreakHandler;
import br.whoslv.vanillapulse.statistic.StatisticService;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class BlockBreakListener {
    private static final BlockBreakHandler handler = new BlockBreakHandler();
    private static final StatisticService statisticService = new StatisticService();

    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            handler.handle(player, state);
            VanillaPulse.LOGGER.info(String.format("%s quebrou %d bloco(s)", player.getName().getString(), statisticService.countBlockBroken(player.getUUID())));
        });
    }
}
