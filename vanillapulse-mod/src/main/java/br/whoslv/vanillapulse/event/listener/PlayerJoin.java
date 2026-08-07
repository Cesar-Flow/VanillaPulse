package br.whoslv.vanillapulse.event.listener;

import br.whoslv.vanillapulse.event.handler.PlayerRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.level.ServerPlayer;

public class PlayerJoin {
    ServerPlayConnectionEvents.Join event = (handler, sender, server) -> {
        ServerPlayer player = handler.player;

        PlayerRegistry playerRegistry = new PlayerRegistry();
        playerRegistry.register(player);
    };
}
