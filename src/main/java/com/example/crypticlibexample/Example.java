package com.example.crypticlibexample;

import crypticlib.BukkitPlugin;
import crypticlib.chat.MessageSender;
import crypticlib.command.CommandInfo;
import crypticlib.command.RootCmdExecutor;
import crypticlib.nms.item.ItemFactory;
import crypticlib.nms.item.NbtItem;
import crypticlib.nms.tile.NbtTileEntity;
import crypticlib.nms.tile.TileEntityFactory;
import crypticlib.ui.display.Icon;
import crypticlib.ui.display.MenuDisplay;
import crypticlib.ui.display.MenuLayout;
import crypticlib.ui.menu.MultipageMenu;
import crypticlib.ui.menu.StoredMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
            .regSub(subcommand("stored-gui")
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
                .setPermission("example.command.stored-gui"))
            .regSub("nbt-item", (sender, args) -> {
                Player player = (Player) sender;
                ItemStack item = player.getInventory().getItemInMainHand();
                NbtItem nbtItem = ItemFactory.item(item);
                nbtItem.nbtTagCompound().set("abc", "abc");
                nbtItem.saveNbtToItem();
                return true;
            })
            .regSub("multipage-gui", (sender, args) -> {
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
            .regSub(subcommand("tile-entity")
                .setExecutor((sender, args) -> {
                    Block block = ((Player) sender).getLocation().getBlock();
                    BlockState state = block.getState();
                    if (!(state instanceof TileState))
                        return true;
                    NbtTileEntity tile = TileEntityFactory.tileEntity(state);
                    MessageSender.sendMsg(sender, tile.nbtTagCompound().toString());
                    tile.nbtTagCompound().set("Items", new ArrayList<>());
                    tile.saveNbtToTileEntity();
                    state.update();
                    return true;
                })
            )
            .setTabCompleter(() -> Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()))
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