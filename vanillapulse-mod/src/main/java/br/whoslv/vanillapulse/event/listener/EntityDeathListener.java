package br.whoslv.vanillapulse.event.listener;

import br.whoslv.vanillapulse.event.handler.DeathHandler;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;

public class EntityDeathListener {
    private static final DeathHandler deathHandler = new DeathHandler();

    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register(deathHandler::handle);
    }
}
