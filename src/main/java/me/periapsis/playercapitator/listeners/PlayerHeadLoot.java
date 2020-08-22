package me.periapsis.playercapitator.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHeadLoot implements Listener {
    @EventHandler
    private void onPlayerDeath( PlayerDeathEvent deathEvent ) {
        Player victim = (Player) deathEvent.getEntity();

        EntityDamageEvent damageEvent = deathEvent.getEntity().getLastDamageCause();
        if ( !( damageEvent instanceof EntityDamageByEntityEvent ) ) {
            return;
        }
        EntityDamageByEntityEvent entityDamageEvent = (EntityDamageByEntityEvent) damageEvent;

        if ( !( entityDamageEvent.getDamager() instanceof Player ) ) {
            return;
        }

        ItemStack skull = new ItemStack( Material.PLAYER_HEAD, 1 );

        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setPlayerProfile( victim.getPlayerProfile() );
        meta.setDisplayName( victim.getDisplayName() + "'s head" );
        skull.setItemMeta( meta );

        World world = victim.getWorld();
        world.dropItem( victim.getLocation(), skull );
    }
}
