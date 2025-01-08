package ch.hatbe.calculatingChat.commands.coordinateCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesCommandTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if(! (sender instanceof Player player)) {
            return null;
        }

        String x = Integer.toString(player.getLocation().getBlockX());
        String y = Integer.toString(player.getLocation().getBlockY());
        String z = Integer.toString(player.getLocation().getBlockZ());

        List<String> suggestions = new ArrayList<>();

        switch(args.length) {
            case 1:
                suggestions.add(x);
                break;
            case 2:
                suggestions.add(y);
                break;
            case 3:
                suggestions.add(z);
                break;
        }

        return suggestions;
    }
}
