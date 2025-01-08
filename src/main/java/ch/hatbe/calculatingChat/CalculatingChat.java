package ch.hatbe.calculatingChat;

import ch.hatbe.calculatingChat.commands.coordinateCommands.NetherCoordinatesCommand;
import ch.hatbe.calculatingChat.commands.coordinateCommands.OverworldCoordinatesCommand;
import ch.hatbe.calculatingChat.events.OnPlayerChat;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CalculatingChat extends JavaPlugin {

    private static CalculatingChat instance;
    private Logger logger;

    @Override
    public void onEnable() {
        this.setInstance(this);
        this.logger = this.getLogger();

        this.getLoggerInstance().info("Plugin enabled");

        this.registerCommands();
        this.registerEvents();
    }

    @Override
    public void onDisable() {
        this.getLoggerInstance().info("Plugin disabled");
    }

    private void registerCommands() {
        this.getCommand("nethercoordinates").setExecutor(new NetherCoordinatesCommand());
        this.getCommand("overworldcoordinates").setExecutor(new OverworldCoordinatesCommand());
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
    }

    private void setInstance(CalculatingChat instance) {
        CalculatingChat.instance = instance;
    }

    public static CalculatingChat getInstance() {
        return instance;
    }

    public Logger getLoggerInstance() {
        return logger;
    }
}
