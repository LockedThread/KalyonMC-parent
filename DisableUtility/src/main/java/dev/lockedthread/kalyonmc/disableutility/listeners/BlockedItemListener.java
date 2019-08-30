package dev.lockedthread.kalyonmc.disableutility.listeners;

import dev.lockedthread.kalyonmc.disableutility.DisableUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class BlockedItemListener implements Listener {

    private static final DisableUtility INSTANCE = DisableUtility.getInstance();

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }
        Player player = event.getPlayer();
        if (item.getType() == Material.GOLDEN_APPLE) {
            if (item.getDurability() == 1) {
                if (INSTANCE.getConfig().getBoolean("block.god-apples.enabled")) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', INSTANCE.getConfig().getString("block.god-apples.message")));
                    if (DisableUtility.getInstance().getConfig().getBoolean("block.god-apples.remove-item")) {
                        player.setItemInHand(null);
                    }
                }
            } else if (INSTANCE.getConfig().getBoolean("block.golden-apples.enabled")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', INSTANCE.getConfig().getString("block.golden-apples.message")));
                if (DisableUtility.getInstance().getConfig().getBoolean("block.golden-apples.remove-item")) {
                    player.setItemInHand(null);
                }
            }
        } else if (item.getType() == Material.POTION) {
            if (item.getItemMeta() instanceof PotionMeta) {
                if (Potion.fromItemStack(item).getType() == PotionType.STRENGTH && INSTANCE.getConfig().getBoolean("block.strength.enabled")) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', INSTANCE.getConfig().getString("block.strength.message")));
                    if (INSTANCE.getConfig().getBoolean("block.strength.remove-item")) {
                        player.setItemInHand(null);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (event.getInventory() == null || event.getRecipe() == null || !(event.getWhoClicked() instanceof Player)) {
            return;
        }
        ItemStack item = event.getRecipe().getResult();
        if (item == null) {
            return;
        }
        if (item.getType() == Material.GOLDEN_APPLE) {
            Player player = (Player) event.getWhoClicked();
            if (item.getDurability() == 1) {
                if (INSTANCE.getConfig().getBoolean("block.god-apples.enabled") && INSTANCE.getConfig().getBoolean("block.god-apples.block-crafting")) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', INSTANCE.getConfig().getString("block.god-apples.message")));
                }
            } else if (INSTANCE.getConfig().getBoolean("block.golden-apples.enabled") && INSTANCE.getConfig().getBoolean("block.golden-apples.block-crafting")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', INSTANCE.getConfig().getString("block.golden-apples.message")));
            }
        }
    }
}
