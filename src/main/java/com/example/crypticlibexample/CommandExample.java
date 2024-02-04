package com.example.crypticlibexample;

import crypticlib.AutoReload;
import crypticlib.Reloadable;
import crypticlib.chat.MessageSender;
import crypticlib.command.CommandHandler;
import crypticlib.command.CommandInfo;
import crypticlib.command.SubcommandHandler;
import crypticlib.command.annotation.Command;
import crypticlib.command.annotation.Subcommand;
import crypticlib.nms.item.ItemFactory;
import crypticlib.nms.item.NbtItem;
import crypticlib.nms.tile.NbtTileEntity;
import crypticlib.nms.tile.TileEntityFactory;
import crypticlib.perm.PermDef;
import crypticlib.perm.PermInfo;
import crypticlib.ui.display.Icon;
import crypticlib.ui.display.MenuDisplay;
import crypticlib.ui.display.MenuLayout;
import crypticlib.ui.menu.MultipageMenu;
import crypticlib.ui.menu.StoredMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static crypticlib.command.manager.CommandManager.subcommand;

@AutoReload
@Command
public class CommandExample extends CommandHandler implements Reloadable {

    @Subcommand
    private SubcommandHandler storedGui = subcommand("stored-gui")
        .setExecutor(
            (sender, args) -> {
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
                return true;
            }
        )
        .setPermission(new PermInfo("example.command.storedgui", PermDef.OP));

    @Subcommand
    private SubcommandHandler nbtItem = subcommand("nbt-item")
        .setExecutor(
            (sender, args) -> {
                Player player = (Player) sender;
                ItemStack item = player.getInventory().getItemInMainHand();
                NbtItem nbtItem = ItemFactory.item(item);
                nbtItem.nbtTagCompound().set("abc", "abc");
                nbtItem.saveNbtToItem();
                return true;
            }
        )
        .setPermission(new PermInfo("example.command.nbtitem", PermDef.OP));

    @Subcommand
    private SubcommandHandler multipageGui = subcommand("multipage-gui")
        .setExecutor(
            (sender, args) -> {
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
            }
        )
        .setPermission(new PermInfo("example.command.multipagegui", PermDef.OP));

    @Subcommand
    private SubcommandHandler tileEntity = subcommand("tile-entity")
        .setExecutor(
            (sender, args) -> {
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
            }
        )
        .setPermission(new PermInfo("example.command.tile-entity", PermDef.OP));

    public CommandExample() {
        super(new CommandInfo("example")
            .setPermission(new PermInfo("example.command"))
            .setAliases(new String[]{"exa"})
            .setDescription("Example command")
            .setUsage("/example or /exa to use")
        );
        setExecutor(
            (sender, args) -> {
                Example.INSTANCE.reloadConfig();
                MessageSender.sendMsg(sender, Configs.test);
                return true;
            }
        );
    }

    @Override
    public void reload() {
        MessageSender.info("Command reloaded");
    }

}
