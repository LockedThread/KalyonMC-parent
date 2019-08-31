package dev.lockedthread.kalyonmc.disableutility.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BlockedEnchantListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEnchantItemEvent(EnchantItemEvent event) {
        if (event.getEnchantsToAdd().containsKey(Enchantment.ARROW_KNOCKBACK) || event.getEnchantsToAdd().containsKey(Enchantment.KNOCKBACK)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getView().getTopInventory();
        if (inventory != null && event.getClickedInventory() != null && inventory.getType() == InventoryType.ANVIL && event.getWhoClicked() instanceof Player) {
            ItemStack firstItem = inventory.getItem(0);
            ItemStack secondItem = inventory.getItem(1);
            if (firstItem != null && secondItem != null && firstItem.getType() != Material.AIR && secondItem.getType() != Material.AIR) {
                ItemStack resultItem = inventory.getItem(2);
                if (resultItem != null && (resultItem.getEnchantments().containsKey(Enchantment.KNOCKBACK) || resultItem.getEnchantments().containsKey(Enchantment.ARROW_KNOCKBACK))) {
                    event.setCancelled(true);
                    event.getWhoClicked().closeInventory();
                }
            }
        }
    }
}
