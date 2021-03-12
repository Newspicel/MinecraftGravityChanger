package net.spaceblock.mc.gravity.gravitychanger;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class GravityChanger extends JavaPlugin {

    private int runableid;

    @Override
    public void onEnable() {
        GravityCommand gravityCommand = new GravityCommand();
        PluginCommand gravity = getCommand("gravity");
        Objects.requireNonNull(gravity).setExecutor(gravityCommand);
        gravity.setTabCompleter(gravityCommand);
        runableid = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new GravityRunnable(), 20L, 20L);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTask(runableid);
    }

}
