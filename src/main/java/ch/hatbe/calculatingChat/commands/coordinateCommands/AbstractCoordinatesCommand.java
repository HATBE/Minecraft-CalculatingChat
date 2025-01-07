package ch.hatbe.calculatingChat.commands.coordinateCommands;

import ch.hatbe.calculatingChat.coordinates.Coordinates2D;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// TODO: if just enter command with no args, it should use players location && add tab complete for player location
// TODO: handle y coordinate

public abstract class AbstractCoordinatesCommand implements CommandExecutor {
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

        Coordinates2D coordinates2D = calculateCoordinates(x, z);
        String dimensionName = this.getDimensionName();

        // TODO: create object && class to handle this shit
        String clipboardText = String.format("%d %d", coordinates2D.x(), coordinates2D.z());
        String displayText = String.format("%s coordinates: x: %d, z: %d", dimensionName, coordinates2D.x(), coordinates2D.z());

        if (sender instanceof Player player) {
            Component message = Component.text(displayText)
                    .hoverEvent(HoverEvent.showText(Component.text("Click to copy coordinates!")))
                    .clickEvent(ClickEvent.copyToClipboard(clipboardText));

            player.sendMessage(message);
        } else {
            sender.sendMessage(displayText);
        }

        return true;
    }



    private String sendUsageMessage(String cmd) {
        return String.format("usage: /%s <x> <z>", cmd);
    }

    protected abstract Coordinates2D calculateCoordinates(int x, int z);

    protected abstract String getDimensionName();
}
