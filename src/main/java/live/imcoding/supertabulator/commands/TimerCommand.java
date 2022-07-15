package live.imcoding.supertabulator.commands;

import live.imcoding.supertabulator.Main;
import live.imcoding.supertabulator.timer.Timer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) {
            sendUsage(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "resume": {
                Timer timer = Main.getInstance().getTimer();

                if (timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "Tiempo ejecutandose.");
                    break;
                }

                timer.setRunning(true);
                sender.sendMessage(ChatColor.GRAY + "Tiempo comenzado.");
                break;
            }
            case "pause": {
                Timer timer = Main.getInstance().getTimer();

                if (!timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "El tiempo no está funcionando.");
                    break;
                }

                timer.setRunning(false);
                sender.sendMessage(ChatColor.GRAY + "El tiempo se ha detenido.");
                break;
            }
            case "time": {
                if(args.length != 2) {
                    sender.sendMessage(ChatColor.GRAY + "Usa" + ChatColor.DARK_GRAY + ": " + ChatColor.BLUE +
                            "/timer time <tiempo>");
                    return true;
                }

                try {
                    Timer timer = Main.getInstance().getTimer();

                    timer.setRunning(false);
                    timer.setTime(Integer.parseInt(args[1]));
                    sender.sendMessage(ChatColor.GRAY + "Se acabo el tiempo " + args[1] + " establecido.");
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "El parámetro 2 debe ser un número.");
                }
                break;
            }
            case "reset": {
                Timer timer = Main.getInstance().getTimer();

                timer.setRunning(false);
                timer.setTime(0);
                sender.sendMessage(ChatColor.GRAY + "El tiempo se ha reiniciado.");
                break;
            }
            default:
                sendUsage(sender);
                break;
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "Usa" + ChatColor.DARK_GRAY + ": " + ChatColor.BLUE +
                "/timer resume, /timer pause, /timer time <Tiempo>, /timer reset");
    }

}
