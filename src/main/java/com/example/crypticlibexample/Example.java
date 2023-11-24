package com.example.crypticlibexample;

import crypticlib.BukkitPlugin;
import crypticlib.command.CommandInfo;
import crypticlib.command.impl.RootCmdExecutor;
import crypticlib.ui.display.Icon;
import crypticlib.ui.display.MenuDisplay;
import crypticlib.ui.display.MenuLayout;
import crypticlib.ui.menu.StoredMenu;
import crypticlib.util.MsgUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static crypticlib.command.CommandManager.subcommand;

public class Example extends BukkitPlugin {

    @Override
    public void enable() {
        saveDefaultConfig();
        new RootCmdExecutor()
            .setExecutor((sender, args) -> {
                MsgUtil.sendMsg(sender, "Hello, CrypticLib!");
                return true;
            })
            .regSub(subcommand("test")
                .setExecutor((sender, args) -> {
                    List<String> layout = new ArrayList<>();
                    layout.add("aaaaaaaaa");
                    layout.add("b       b");
                    layout.add("ccccccccc");
                    Map<Character, Icon> layoutMap = new ConcurrentHashMap<>();
                    layoutMap.put('a', new Icon(new ItemStack(Material.STONE), event -> {
                        MsgUtil.sendMsg(event.getWhoClicked(), "Click Stone");
                    }));
                    layoutMap.put('b', new Icon(new ItemStack(Material.IRON_INGOT), event -> {
                        MsgUtil.sendMsg(event.getWhoClicked(), "Click Iron_ingot");
                    }));
                    layoutMap.put('c', new Icon(new ItemStack(Material.DIAMOND), event -> {
                        MsgUtil.sendMsg(event.getWhoClicked(), "Click diamond");
                    }));
                    MenuDisplay display = new MenuDisplay("Hello, %player_displayname%", new MenuLayout(layout, layoutMap));
                    new StoredMenu((Player) sender, display).openMenu();
                    MsgUtil.sendMsg(sender, "CrypticLib Test!");
                    return true;
                })
                .regSub("test", (sender, args) -> {
                    MsgUtil.sendMsg(sender, "CrypticLib Test Test!");
                    return true;
                })
                .setPermission("example.command.test")
                .addTabArguments("abc"))
            .regSub("test2", (sender, args) -> {
                MsgUtil.sendMsg(sender, "CrypticLib Test2!");
                return true;
            })
            .register(
                this,
                new CommandInfo("example", "example.command", new String[]{"exa"}, "Example command", "/example or /exa")
            );
        ;
    }

    @Override
    public void disable() {
        //TODO
    }

}