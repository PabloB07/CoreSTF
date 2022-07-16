package live.imcoding.supertabulator.commands;

import live.imcoding.supertabulator.Main;
import live.imcoding.supertabulator.backpack.Backpack;
import live.imcoding.supertabulator.utils.HexColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackpackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        Backpack backpack = Main.getInstance().getBackpackManager().getBackpack(player.getUniqueId());

        if (player.hasPermission("stf.command.mochila")){
            player.openInventory(backpack.getInventory());
        }else{
            player.sendMessage(HexColor.format("#8a0b1a No tienes permisos!"));
        }
        return true;
    }

}
