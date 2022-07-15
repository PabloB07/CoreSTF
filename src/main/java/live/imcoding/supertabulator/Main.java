package live.imcoding.supertabulator;

import live.imcoding.supertabulator.backpack.BackpackManager;
import live.imcoding.supertabulator.commands.BackpackCommand;
import live.imcoding.supertabulator.commands.TimerCommand;
import live.imcoding.supertabulator.scoreboard.FastBoard;
import live.imcoding.supertabulator.timer.Timer;
import live.imcoding.supertabulator.utils.Config;
import live.imcoding.supertabulator.utils.HexColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.Statistic;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Main extends JavaPlugin  implements Listener {

    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private static Main instance;

    private Timer timer;
    private Config config;
    private BackpackManager backpackManager;

    @Override
    public void onLoad() {
        instance = this;
        config = new Config();
    }
    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin activado!");

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new HexColor(), this);
        manager.registerEvents(this, this);

        getCommand("timer").setExecutor(new TimerCommand());
        getCommand("mochila").setExecutor(new BackpackCommand());

        timer = new Timer();
        backpackManager = new BackpackManager();

        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }

    @Override
    public void onDisable() {
        timer.save();
        backpackManager.save();

        config.save();
    }

    public static Main getInstance() {
        return instance;
    }

    public Config getConfiguration() {
        return config;
    }

    public Timer getTimer() {
        return timer;
    }

    public BackpackManager getBackpackManager() {
        return backpackManager;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        FastBoard board = new FastBoard(player);

        e.setJoinMessage(HexColor.format("#dea428").toString() + player.getDisplayName() + " entró ✔");

        player.sendMessage(HexColor.format("#de7728").toString() + "✠ Bienvenido al servidor de STF ✠");

        board.updateTitle(HexColor.format("#de2856").toString() + ChatColor.BOLD + " STF ");

        this.boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        e.setQuitMessage(HexColor.format("#dea428").toString() + player.getDisplayName() + " se fué ✘");


        FastBoard board = this.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    private void updateBoard(FastBoard board) {

        board.updateLines(
                HexColor.format(""),
                HexColor.format("#de2856Online» " + getServer().getOnlinePlayers().size()),
                "",
                HexColor.format("#de2856Kills» " + board.getPlayer().getStatistic(Statistic.PLAYER_KILLS)),
                "",
                HexColor.format("#de2856Latencia» " + board.getPlayer().getPing()),
                "",
                HexColor.format("#de2856Nombre» " + board.getPlayer().getDisplayName()),
                "",
                HexColor.format("#fcba03twitch.tv/spectre242")
        );
    }

}

