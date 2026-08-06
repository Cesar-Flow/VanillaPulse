package br.whoslv.vanillapulse.event.handler;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class DeathHandler {
    private final PlayerKillHandler playerKillHandler;
    private final PlayerDeathHandler playerDeathHandler;
    private final MobKillHandler mobKillHandler;

    public DeathHandler() {
        playerKillHandler = new PlayerKillHandler();
        mobKillHandler = new MobKillHandler();
        playerDeathHandler = new PlayerDeathHandler();
    }

    public void handle(LivingEntity entity, DamageSource damageSource) {
        if (entity instanceof Player player) {
            handlePlayerDeath(player, damageSource);
        } else {
            handleMobDeath(entity, damageSource);
        }
    }

    private void handlePlayerDeath(Player player, DamageSource damageSource) {
        var attacker = damageSource.getEntity();

        if (attacker instanceof Player killer) {
            playerKillHandler.handle(killer, player);
        } else {
            playerDeathHandler.handle(player, damageSource);
        }
    }

    private void handleMobDeath(LivingEntity mob, DamageSource damageSource) {
        var attacker = damageSource.getEntity();

        if (attacker instanceof Player player) {
            mobKillHandler.handle(player, mob);
        }
    }
}
