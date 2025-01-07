package ch.hatbe.calculatingChat.commands;

import ch.hatbe.calculatingChat.coordinates.CoordinateCalculator;
import ch.hatbe.calculatingChat.coordinates.Coordinates2D;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class OverworldCoordinatesCommand implements CommandExecutor {
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

        Coordinates2D coordinates2D = CoordinateCalculator.calculateOverworldCoordinates(x, z);

        sender.sendMessage(String.format("Overworld coordinates: x: %d, z: %d", coordinates2D.x(), coordinates2D.z()));

        return true;
    }



    private String sendUsageMessage(String cmd) {
        return String.format("usage: /%s <x> <z>", cmd);
    }
}
