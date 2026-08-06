package br.whoslv.vanillapulse.event.listener;

import br.whoslv.vanillapulse.event.handler.BlockBreakHandler;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class BlockBreakListener {
    private static final BlockBreakHandler blockBreakHandler = new BlockBreakHandler();

    public static void register() {
        PlayerBlockBreakEvents.AFTER.register(
                (world, player, pos, state, entity) -> {
                    blockBreakHandler.handle(player);
                }
        );

    }
}