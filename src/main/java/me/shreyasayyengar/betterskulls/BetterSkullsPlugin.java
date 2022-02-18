package me.shreyasayyengar.betterskulls;

import me.shreyasayyengar.betterskulls.commands.SkullCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterSkullsPlugin extends JavaPlugin {

    private static BetterSkullsPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        registerCommands();

    }

    private void registerCommands() {
        this.getCommand("skull").setExecutor(new SkullCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
