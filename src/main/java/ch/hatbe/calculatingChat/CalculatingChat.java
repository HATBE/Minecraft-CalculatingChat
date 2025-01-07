package ch.hatbe.calculatingChat;

import ch.hatbe.calculatingChat.commands.NetherCoordinatesCommand;
import ch.hatbe.calculatingChat.commands.OverworldCoordinatesCommand;
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
    }

    @Override
    public void onDisable() {
        this.getLoggerInstance().info("Plugin disabled");
    }

    private void registerCommands() {
        this.getCommand("nethercoordinates").setExecutor(new NetherCoordinatesCommand());
        this.getCommand("overworldcoordinates").setExecutor(new OverworldCoordinatesCommand());
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
