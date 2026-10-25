package com.example.customitems;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomItemListener implements Listener {

    private final JavaPlugin plugin;

    public CustomItemListener(JavaPlugin plugin) {
        this.plugin = plugin;
        startCrownTask();
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Player killer = entity.getKiller();
        if (killer == null) return;

        ItemStack weapon = killer.getInventory().getItemInMainHand();
        if (ItemManager.isCustomItem(weapon, "vampire_sword")) {
            killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
            killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0));
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack item = event.getItem();

            if (ItemManager.isCustomItem(item, "hammer_of_gods")) {
                event.setCancelled(true);

                if (player.hasCooldown(item.getType())) return;
                player.setCooldown(item.getType(), 40);

                Block target = player.getTargetBlockExact(50);
                Location loc = target != null ? target.getLocation() : player.getLocation().add(player.getLocation().getDirection().multiply(10));

                player.getWorld().strikeLightning(loc);
            }
        }
    }

    private void startCrownTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    ItemStack helmet = player.getInventory().getHelmet();
                    if (ItemManager.isCustomItem(helmet, "crown")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 60, 0, true, false));
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}
