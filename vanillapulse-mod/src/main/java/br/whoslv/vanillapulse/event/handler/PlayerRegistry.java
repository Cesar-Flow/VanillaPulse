package br.whoslv.vanillapulse.event.handler;

import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerRegistry {
    private final Map<UUID, ServerPlayer> players = new HashMap<>();

    public void register(ServerPlayer player) {
        players.put(player.getUUID(), player);
    }

    public Map<UUID, ServerPlayer> getAllPlayers() {
        return players;
    }
}
