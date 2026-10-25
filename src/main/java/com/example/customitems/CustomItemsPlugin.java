package com.example.customitems;

import org.bukkit.plugin.java.JavaPlugin;

public final class CustomItemsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        ItemManager.init(this);
        getServer().getPluginManager().registerEvents(new CustomItemListener(this), this);

        if (getCommand("customitem") != null) {
            getCommand("customitem").setExecutor(new CustomItemCommand());
        }

        getLogger().info("CustomItemsPlugin został pomyślnie włączony!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomItemsPlugin został wyłączony.");
    }
}
