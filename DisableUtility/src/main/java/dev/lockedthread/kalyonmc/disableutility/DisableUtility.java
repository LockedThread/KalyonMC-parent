package dev.lockedthread.kalyonmc.disableutility;

import dev.lockedthread.kalyonmc.disableutility.listeners.BlockedEnchantListener;
import dev.lockedthread.kalyonmc.disableutility.listeners.BlockedItemListener;
import org.bukkit.plugin.java.JavaPlugin;

public class DisableUtility extends JavaPlugin {

    private static DisableUtility instance;

    public static DisableUtility getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if (getConfig().getBoolean("block.knockback.enabled")) {
            getServer().getPluginManager().registerEvents(new BlockedEnchantListener(), this);
        }
        getServer().getPluginManager().registerEvents(new BlockedItemListener(), this);
        instance = this;
    }

    @Override
    public void onDisable() {

        instance = null;
    }
}
