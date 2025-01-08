package ch.hatbe.calculatingChat.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.ArrayList;
import java.util.List;

public class OnPlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        String message = Component.text().content(event.message().toString()).toString();

        System.out.println("Message: " + message);

        if (message.contains("=")) {
            String[] parts = message.split("=", 2);
            String query = parts.length > 1 ? parts[1].trim() : "";

            List<String> suggestions = getSuggestions(query);

            if (!suggestions.isEmpty()) {
                event.getPlayer().sendMessage(Component.text("Suggestions for '" + query + "':"));
                for (String suggestion : suggestions) {
                    event.getPlayer().sendMessage(Component.text("- " + suggestion));
                }
            }
        }
    }

    private List<String> getSuggestions(String input) {
        // Example suggestions based on input
        List<String> suggestions = new ArrayList<>();
        if ("apple".startsWith(input.toLowerCase())) {
            suggestions.add("apple");
        }
        if ("banana".startsWith(input.toLowerCase())) {
            suggestions.add("banana");
        }
        if ("cherry".startsWith(input.toLowerCase())) {
            suggestions.add("cherry");
        }
        return suggestions;
    }
}
