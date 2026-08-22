package example;

import crypticlib.BukkitPlugin;
import crypticlib.CrypticLib;
import crypticlib.CrypticLibBukkit;
import crypticlib.MinecraftVersion;
import org.bukkit.event.Listener;

public final class Example extends BukkitPlugin implements Listener {

    @Override
    public void whenEnable() {
        CrypticLib.info("Server Type: " + CrypticLibBukkit.serverAdapter().type().name());
        CrypticLib.info("Server Version: " + MinecraftVersion.CURRENT.name());
    }

    @Override
    public void whenDisable() {
        CrypticLib.info("Plugin disabled.");
    }

}