package com.example.crypticlibexample;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import crypticlib.BukkitPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Example extends BukkitPlugin {

    public static Example INSTANCE;

    @Override
    public void enable() {
        INSTANCE = this;
        ProtocolLibrary.getProtocolManager().addPacketListener(
            new PacketAdapter(
                this,
                PacketType.Play.Client.WINDOW_CLICK
            ) {
                @Override
                public void onPacketReceiving(PacketEvent event) {
                    PacketContainer packet = event.getPacket();
                    Integer slot = packet.getIntegers().read(2);
                    Player player = event.getPlayer();
                    InventoryHolder holder = player.getOpenInventory().getTopInventory().getHolder();
                    if (holder == null)
                        return;
                    if (!(holder instanceof TestMenu))
                        return;
                    TestMenu menu = (TestMenu) holder;
                    int size = menu.inventoryCache().getSize();
                    if (slot >= size) {
                        event.setCancelled(true);
                        menu.updateMenu();
                        menu.sendCursor();
                        int rawSlot = slot - size;
                        menu.onClickPlayerInv(rawSlot);
                    }
//                    short slot = event.getPacket().getIntegers().read(0);
//                    System.out.println(slot);
                }
            }
        );
        ProtocolLibrary.getProtocolManager().addPacketListener(
            new PacketAdapter(
                this,
                PacketType.Play.Server.WINDOW_ITEMS
            ) {
                @Override
                public void onPacketSending(PacketEvent event) {
                    PacketContainer packet = event.getPacket();
                    List<String> layout = Configs.LAYOUT.value();
                    if (!(event.getPlayer().getOpenInventory().getTopInventory().getHolder() instanceof TestMenu))
                        return;
                    List<ItemStack> items = packet.getItemListModifier().read(0);
                    for (int i = 0; i < 4; i++) {
                        String line;
                        if (i >= layout.size()) {
                            line = "         ";
                        } else {
                            line = layout.get(i);
                        }

                        int startIndex = items.size() - 36;
                        for (int j = 0; j < line.length() && j < 9; j++) {
                            char c = line.charAt(j);
                            int slot = startIndex + i * 9 + j;
                            if (c == '#') {
                                items.set(slot, new ItemStack(Material.STONE));
                            } else {
                                items.set(slot, new ItemStack(Material.AIR));
                            }
                        }
                    }
                    packet.getItemListModifier().write(0, items);
                }
            }
        );
    }

    @Override
    public void disable() {
        //TODO
    }

}