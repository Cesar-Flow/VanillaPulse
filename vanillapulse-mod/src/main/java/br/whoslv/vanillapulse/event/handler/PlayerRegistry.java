package br.whoslv.vanillapulse.event.handler;

import br.whoslv.vanillapulse.VanillaPulse;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerRegistry {

    public void register(ServerPlayer player) {
        VanillaPulse.playerRepository.registerPlayer(player);
    }

    public Map<UUID, ServerPlayer> getAllPlayers() {
        Set<UUID> uuids = VanillaPulse.playerRepository.getAllPlayers();

        Map<UUID, ServerPlayer> players = new HashMap<>();

        for (UUID uuid : uuids) {
            ServerPlayer player = VanillaPulse.server.getPlayerList().getPlayer(uuid);

            if (player != null) {
                players.put(uuid, player);
            }
        }

        return players;
    }
}
