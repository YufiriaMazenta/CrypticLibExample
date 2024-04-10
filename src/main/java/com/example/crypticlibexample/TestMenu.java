package com.example.crypticlibexample;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import crypticlib.CrypticLib;
import crypticlib.ui.display.Icon;
import crypticlib.ui.display.MenuDisplay;
import crypticlib.ui.display.MenuLayout;
import crypticlib.ui.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMenu extends Menu {
    public TestMenu(@NotNull Player player) {
        super(player, new MenuDisplay(
            "Hello",
            new MenuLayout(
                Arrays.asList("#########", "#########", "########"),
                new HashMap<>()
            )
        ));
        Map<Character, Icon> map = new HashMap<>();
        map.put('#', new Icon(Material.GOLD_BLOCK));
        this.display.layout().setLayoutMap(map);
    }

    public void onClickPlayerInv(int rawSlot) {
        System.out.println("Clicked " + rawSlot);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        CrypticLib.platform().scheduler().runTask(
            Example.INSTANCE,
            () -> {
                sendPlayerInv();
                sendCursor();
            }
        );
    }

    public void sendPlayerInv() {
        PacketContainer packetContainer = new PacketContainer(
            PacketType.Play.Server.WINDOW_ITEMS
        );
        List<ItemStack> items = packetContainer.getItemListModifier().read(0);
        int menuSize = display.layout().layout().size() * 9;
        for (int i = 0; i < menuSize; i++) {
            items.add(i, new ItemStack(Material.AIR));
        }
        List<String> layout = Configs.LAYOUT.value();
        for (int i = 0; i < 4; i++) {
            String line;
            if (i >= layout.size()) {
                line = "         ";
            } else {
                line = layout.get(i);
            }

            for (int j = 0; j < line.length() && j < 9; j++) {
                char c = line.charAt(j);
                int slot = menuSize + i * 9 + j;
                if (c == '#') {
                    items.add(slot, new ItemStack(Material.STONE));
                } else {
                    items.add(slot, new ItemStack(Material.AIR));
                }
            }
        }
        packetContainer.getItemListModifier().write(0, items);
        ProtocolLibrary.getProtocolManager().sendServerPacket(
            player,
            packetContainer
        );

    }

    public void sendCursor() {
        PacketContainer packetContainer = new PacketContainer(
            PacketType.Play.Server.SET_SLOT
        );
        packetContainer.getItemModifier().write(0, new ItemStack(Material.AIR));
        packetContainer.getIntegers().write(0, -1);
        ProtocolLibrary.getProtocolManager().sendServerPacket(
            player,
            packetContainer
        );
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        CrypticLib.platform().scheduler().runTask(
            Example.INSTANCE,
            player::updateInventory
        );
    }

    @Override
    public Icon onClick(int slot, InventoryClickEvent event) {
        if (!event.getView().getTopInventory().equals(event.getClickedInventory())) {
            event.setCancelled(true);
            return null;
        } else if (!this.slotMap.containsKey(slot)) {
            event.setCancelled(true);
            return null;
        } else {
            event.setCancelled(true);
            return ((Icon)this.slotMap.get(slot)).onClick(event);
        }
    }
}
