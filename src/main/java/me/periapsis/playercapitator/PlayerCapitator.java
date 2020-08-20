package me.periapsis.playercapitator;

import me.periapsis.playercapitator.listeners.PlayerHeadLoot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCapitator extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents( new PlayerHeadLoot(), this );
    }

    @Override
    public void onDisable() {
    }
}
