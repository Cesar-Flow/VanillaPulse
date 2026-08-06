package br.whoslv.vanillapulse.event.handler;

import br.whoslv.vanillapulse.statistic.StatisticService;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class BlockBreakHandler {
    private final StatisticService statisticService;

    public BlockBreakHandler() {
        this.statisticService = new StatisticService();

    }

    public void handle(Player player, BlockState block) {
        String blockName = block.getBlock().getName().toString();

        statisticService.addBlockBroken(player, blockName);
    }
}
