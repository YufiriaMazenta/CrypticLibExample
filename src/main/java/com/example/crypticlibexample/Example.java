package com.example.crypticlibexample;

import crypticlib.BukkitPlugin;
import crypticlib.chat.MessageSender;
import crypticlib.command.CommandInfo;
import crypticlib.command.impl.RootCmdExecutor;
import crypticlib.nms.item.ItemFactory;
import crypticlib.nms.item.NbtItem;
import crypticlib.ui.display.Icon;
import crypticlib.ui.display.MenuDisplay;
import crypticlib.ui.display.MenuLayout;
import crypticlib.ui.menu.MultipageMenu;
import crypticlib.ui.menu.StoredMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static crypticlib.command.CommandManager.subcommand;

public class Example extends BukkitPlugin {

    @Override
    public void enable() {
        new RootCmdExecutor()
            .setExecutor((sender, args) -> {
                reloadConfig();
                MessageSender.sendMsg(sender, Configs.test);
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
                        MessageSender.sendMsg(event.getWhoClicked(), "Click Stone");
                    }));
                    layoutMap.put('b', new Icon(new ItemStack(Material.IRON_INGOT), event -> {
                        MessageSender.sendMsg(event.getWhoClicked(), "Click Iron_ingot");
                    }));
                    layoutMap.put('c', new Icon(new ItemStack(Material.DIAMOND), event -> {
                        MessageSender.sendMsg(event.getWhoClicked(), "Click diamond");
                    }));
                    MenuDisplay display = new MenuDisplay("Hello, %player_displayname%", new MenuLayout(layout, layoutMap));
                    new StoredMenu((Player) sender, display).openMenu();
                    MessageSender.sendMsg(sender, "CrypticLib Test!");
                    return true;
                })
                .regSub("test", (sender, args) -> {
                    MessageSender.sendMsg(sender, "CrypticLib Test Test!");
                    return true;
                })
                .setPermission("example.command.test")
                .addTabArguments("abc"))
            .regSub("test2", (sender, args) -> {
                Player player = (Player) sender;
                ItemStack item = player.getInventory().getItemInMainHand();
                NbtItem nbtItem = ItemFactory.item(item);
                nbtItem.nbtTagCompound().set("abc", "abc");
                nbtItem.saveNbtToBukkit();
                return true;
            })
            .regSub("gui", (sender, args) -> {
                List<Icon> icons = new ArrayList<>();
                for (Material value : Material.values()) {
                    if (!value.isItem())
                        continue;
                    if (value.equals(Material.AIR))
                        continue;
                    icons.add(new Icon(value, value.name(), event -> {
                        MessageSender.sendMsg(event.getWhoClicked(), value.name());
                    }));
                }
                MultipageMenu menu = new MultipageMenu(
                    (Player) sender,
                    new MenuDisplay(
                        "title",
                        new MenuLayout(
                            Arrays.asList(
                                "A#######B",
                                "#########",
                                "#########",
                                "#########",
                                "#########"
                            ),
                            () -> {
                                Map<Character, Icon> map = new HashMap<>();
                                Icon pre = new Icon(Material.STONE, "previous", event -> {
                                    MultipageMenu holder = (MultipageMenu) event.getClickedInventory().getHolder();
                                    holder.previousPage();
                                });
                                Icon next = new Icon(Material.STONE, "next", event -> {
                                    MultipageMenu holder = (MultipageMenu) event.getClickedInventory().getHolder();
                                    holder.nextPage();
                                });
                                map.put('A', pre);
                                map.put('B', next);
                                return map;
                            }
                        )
                    ),
                    '#',
                    icons
                );
                menu.openMenu();
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