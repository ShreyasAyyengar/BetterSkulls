package me.shreyasayyengar.betterskulls.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

public class SkullCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (args.length == 1) {

                if (player.getInventory().firstEmpty() == -1) {
                    player.sendMessage(ChatColor.RED + "Failed! Your inventory is full!");
                    return false;
                }

                if (!player.hasPermission("skull.self")) {
                    player.sendMessage(ChatColor.RED + "Failed! You do not have permission!");
                    return false;
                }

                if (player.getInventory().getItemInMainHand().getType() == Material.PLAYER_HEAD) {
                    createSkull(args, player, true);
                    return false;
                }

                createSkull(args, player, false);
            }

            if (args.length == 2) {

                if (!player.hasPermission("skull.others")) {
                    player.sendMessage(ChatColor.RED + "Failed! You do not have permission!");
                }
                if (player.getInventory().firstEmpty() == -1) {
                    player.sendMessage(ChatColor.RED + "Failed! Your inventory is full!");
                    return false;
                }

                if (!Bukkit.getOfflinePlayer(args[1]).isOnline()) {
                    player.sendMessage(ChatColor.RED + "Failed! That player is not online");
                    return false;
                }

                Player target = Bukkit.getPlayer(args[1]);
                createSkull(args, target, false);
            }

        } else sender.sendMessage("You cannot use this command through the console!");

        return false;
    }

    private void createSkull(@NotNull String[] args, Player target, boolean replace) {
        OfflinePlayer targetSkull = Bukkit.getOfflinePlayer(args[0]);
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) stack.getItemMeta();
        itemMeta.setOwningPlayer(targetSkull);
        stack.setItemMeta(itemMeta);

        if (replace) {
            target.getInventory().setItemInMainHand(stack);
        } else {
            target.getInventory().addItem(stack);
        }

    }
}
