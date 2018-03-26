package com.aaomidi.blockcreator.engine;

import com.aaomidi.blockcreator.BlockCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandHandler implements CommandExecutor {
    private final BlockCreator instance;

    public CommandHandler(BlockCreator instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        for (ItemStack item : player.getInventory()) {
            if (item == null) continue;

            ItemStack[] result = instance.getHandler().handle(item);
            if (result == null) continue;

            player.getInventory().addItem(result[0]);
            player.getInventory().removeItem(result[1]);
        }
        return true;
    }
}
