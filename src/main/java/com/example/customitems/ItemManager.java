package com.example.customitems;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ItemManager {

    private static JavaPlugin plugin;
    private static NamespacedKey key;

    public static void init(JavaPlugin p) {
        plugin = p;
        key = new NamespacedKey(plugin, "custom_item_id");
    }

    public static ItemStack getVampireSword() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Miecz Wampira", NamedTextColor.DARK_RED).decoration(TextDecoration.BOLD, true));
        meta.lore(List.of(Component.text("Po zabójstwie wroga daje efekt złotego jabłka.", NamedTextColor.GRAY)));
        meta.setCustomModelData(1); // Powiązanie z teksturą
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "vampire_sword");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getCrown() {
        ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Korona", NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true));
        meta.lore(List.of(Component.text("Daje 2 dodatkowe serca oraz efekt szczęścia.", NamedTextColor.GRAY)));
        meta.setCustomModelData(2); // Powiązanie z teksturą

        NamespacedKey attrKey = new NamespacedKey(plugin, "crown_health");
        AttributeModifier modifier = new AttributeModifier(attrKey, 4.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);

        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "crown");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getHammerOfGods() {
        ItemStack item = new ItemStack(Material.MACE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Młot Bogów", NamedTextColor.YELLOW).decoration(TextDecoration.BOLD, true));
        meta.lore(List.of(Component.text("Kliknij PPM, aby przywołać piorun w miejscu patrzenia.", NamedTextColor.GRAY)));
        meta.setCustomModelData(3); // Powiązanie z teksturą
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "hammer_of_gods");
        item.setItemMeta(meta);
        return item;
    }

    public static boolean isCustomItem(ItemStack item, String id) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        String value = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        return id.equals(value);
    }
}
