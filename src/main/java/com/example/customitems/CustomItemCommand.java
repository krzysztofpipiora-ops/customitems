package com.example.customitems;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CustomItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Ta komenda jest dostępna tylko dla graczy.");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(Component.text("Użycie: /customitem <sword|crown|hammer>", NamedTextColor.YELLOW));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "sword", "vampire":
                player.getInventory().addItem(ItemManager.getVampireSword());
                player.sendMessage(Component.text("Otrzymałeś Miecz Wampira!", NamedTextColor.GREEN));
                break;
            case "crown":
                player.getInventory().addItem(ItemManager.getCrown());
                player.sendMessage(Component.text("Otrzymałeś Koronę!", NamedTextColor.GREEN));
                break;
            case "hammer", "gods":
                player.getInventory().addItem(ItemManager.getHammerOfGods());
                player.sendMessage(Component.text("Otrzymałeś Młot Bogów!", NamedTextColor.GREEN));
                break;
            default:
                player.sendMessage(Component.text("Nieznany przedmiot! Dostępne: sword, crown, hammer", NamedTextColor.RED));
                break;
        }
        return true;
    }
}
