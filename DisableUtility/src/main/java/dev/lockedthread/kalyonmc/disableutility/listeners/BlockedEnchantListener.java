package dev.lockedthread.kalyonmc.disableutility.listeners;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import java.util.Map;

public class BlockedEnchantListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEnchantItemEvent(EnchantItemEvent event) {
        for (Map.Entry<Enchantment, Integer> entry : event.getEnchantsToAdd().entrySet()) {
            if (entry.getKey().getName().equalsIgnoreCase("knockback")) {
                event.getEnchantsToAdd().remove(entry.getKey());
            }
        }
    }
}
