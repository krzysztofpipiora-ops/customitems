public static ItemStack getVampireSword() {
    ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
    ItemMeta meta = item.getItemMeta();
    meta.displayName(Component.text("Miecz Wampira", NamedTextColor.DARK_RED).decoration(TextDecoration.BOLD, true));
    meta.lore(List.of(Component.text("Po zabójstwie wroga daje efekt złotego jabłka.", NamedTextColor.GRAY)));
    
    // Dodajemy CustomModelData (np. numer 1)
    meta.setCustomModelData(1);
    
    meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "vampire_sword");
    item.setItemMeta(meta);
    return item;
}

public static ItemStack getCrown() {
    ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
    ItemMeta meta = item.getItemMeta();
    meta.displayName(Component.text("Korona", NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true));
    meta.lore(List.of(Component.text("Daje 2 dodatkowe serca oraz efekt szczęścia.", NamedTextColor.GRAY)));
    
    // CustomModelData (np. numer 2)
    meta.setCustomModelData(2);

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
    
    // CustomModelData (np. numer 3)
    meta.setCustomModelData(3);
    
    meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "hammer_of_gods");
    item.setItemMeta(meta);
    return item;
}
