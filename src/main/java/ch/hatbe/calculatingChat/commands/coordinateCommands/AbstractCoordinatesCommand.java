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

// TODO: add tab complete for player location
// TODO: handle y coordinate

public abstract class AbstractCoordinatesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        int x, z;

        switch(args.length) {
            case 0:
                if (sender instanceof Player player) {
                    x = player.getLocation().getBlockX();
                    z = player.getLocation().getBlockZ();
                } else {
                    sender.sendMessage(this.sendUsageMessage(label));
                    return false;
                }
                break;
            case 2:
                try {
                    x = Integer.parseInt(args[0]);
                    z = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Error: Coordinates must be valid integers.");
                    return false;
                }
                break;
            default:
                sender.sendMessage(this.sendUsageMessage(label));
                return false;
        }

        Coordinates2D coordinates = this.calculateCoordinates(x, z);
        String dimensionName = this.getDimensionName();

        // TODO: create object && class to handle this shit
        String clipboardText = String.format("%d %d", coordinates.x(), coordinates.z());
        String displayText = String.format("%s coordinates: x: %d, z: %d", dimensionName, coordinates.x(), coordinates.z());

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
