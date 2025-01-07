package ch.hatbe.calculatingChat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;

public class NetherCoordinatesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(this.sendUsageMessage(label));
            return false;
        }

        int x, z;

        try {
            x = Integer.parseInt(args[0]);
            z = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("Error: Coordinates must be valid integers.");
            return false;
        }

        NetherCoordinates netherCoordinates = this.calculateNetherCoordinates(x, z);

        sender.sendMessage(String.format("Nether coordinates: x: %d, z: %d", netherCoordinates.x(), netherCoordinates.z()));

        return true;
    }

    public NetherCoordinates calculateNetherCoordinates(int x, int z) {
        int netherX = x / 8;
        int netherZ = z / 8;

        return new NetherCoordinates(netherX, netherZ);
    }

    public String sendUsageMessage(String cmd) {
        return String.format("usage: /%s <x> <z>", cmd);
    }
}
