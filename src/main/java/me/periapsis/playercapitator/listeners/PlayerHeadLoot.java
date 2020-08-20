package me.periapsis.playercapitator.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHeadLoot implements Listener {
    @EventHandler
    private void onPlayerDeath( EntityDamageByEntityEvent event ) {
        Entity ent = event.getEntity();
        Entity killer = event.getDamager();

        if ( ent instanceof Player && killer instanceof Player ) {
            Player ply = (Player) ent;

            if ( ply.getHealth() - event.getDamage() <= 0 ) {
                ItemStack skull = new ItemStack( Material.PLAYER_HEAD, 1 );

                SkullMeta meta = (SkullMeta) skull.getItemMeta();
                meta.setPlayerProfile( ply.getPlayerProfile() );
                meta.setDisplayName( ply.getDisplayName() + "'s head" );
                skull.setItemMeta( meta );

                World world = ply.getWorld();
                world.dropItem( ply.getLocation(), skull );
            }
        }
    }
}
