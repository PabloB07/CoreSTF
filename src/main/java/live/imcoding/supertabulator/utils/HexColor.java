package live.imcoding.supertabulator.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexColor implements Listener {
    public HexColor() {
    }

    public static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().hasPermission("hex"))
        event.setMessage(format(event.getMessage()));
    }

    public static String format(String msg) {
        if (Bukkit.getVersion().contains("1.19")) {
            //HEX color now
            Matcher matcher = HEX_PATTERN.matcher(msg);
            while (matcher.find()) {
                String color = msg.substring(matcher.start(), matcher.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                matcher = HEX_PATTERN.matcher(msg);
            }
        }
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', msg);
    }
}
