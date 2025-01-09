package com.example.crypticlibexample;

import crypticlib.chat.BukkitMsgSender;
import crypticlib.chat.BukkitTextProcessor;
import crypticlib.command.BukkitCommand;
import crypticlib.command.BukkitSubcommand;
import crypticlib.command.CommandInfo;
import crypticlib.command.annotation.Command;
import crypticlib.command.annotation.Subcommand;
import crypticlib.perm.PermInfo;
import crypticlib.util.ItemHelper;
import crypticlib.util.JsonHelper;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Command
public class ExampleCommand extends BukkitCommand {

    public ExampleCommand() {
        super(new CommandInfo(
            "test",
            new PermInfo("test.test"),
            Arrays.asList("tt"),
            "测试命令",
            "用法: &r/test &7<...>"
            )
        );
    }

    @Subcommand
    BukkitSubcommand reload = new BukkitSubcommand(
        new CommandInfo(
            "reload",
            new PermInfo("test.test.reload"),
            Collections.emptyList(),
            "&7重载插件"
        )
    ) {
        @Override
        public void execute(@NotNull CommandSender sender, @NotNull List<String> args) {
            Example.INSTANCE.reloadPlugin();
            BukkitMsgSender.INSTANCE.sendMsg(sender, ExampleConfig.test.value());
        }
    };

    @Subcommand
    BukkitSubcommand test = new BukkitSubcommand("test2") {
        @Override
        public void execute(@NotNull CommandSender sender, @NotNull List<String> args) {
            BukkitMsgSender.INSTANCE.sendMsg(sender, ExampleConfig.test2.value().toString());
        }
    };

    @Subcommand
    BukkitSubcommand showItem = new BukkitSubcommand("test3") {
        @Override
        public void execute(@NotNull CommandSender commandSender, @NotNull List<String> args) {
            if (!(commandSender instanceof Player player)) {
                BukkitMsgSender.INSTANCE.sendMsg(commandSender, "Player only");
                return;
            }
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            if (ItemHelper.isAir(itemInMainHand)) {
                BukkitMsgSender.INSTANCE.sendMsg(commandSender, "Item is air");
                return;
            }

            BaseComponent component = getItemNameComponent(itemInMainHand);
            if (itemInMainHand.hasItemMeta()) {
                ItemMeta itemMeta = itemInMainHand.getItemMeta();
//                BukkitMsgSender.INSTANCE.info("NBT: " + itemMeta.getAsString());
//                BukkitMsgSender.INSTANCE.info("Components: " + itemMeta.getAsComponentString());
                String typeKey = itemInMainHand.getType().getKey().toString();
                Item itemNbt = new Item(typeKey, itemInMainHand.getAmount(), ItemTag.ofNbt(itemMeta.getAsString()));
                BaseComponent nbtComponent = component.duplicate();
                BukkitMsgSender.INSTANCE.info(JsonHelper.getGson().toJson(itemNbt));
                BukkitMsgSender.INSTANCE.sendMsg(player, BukkitTextProcessor.setHoverEvent(nbtComponent, new HoverEvent(HoverEvent.Action.SHOW_ITEM, itemNbt)));

                Item itemComponent = new Item(typeKey, itemInMainHand.getAmount(), ItemTag.ofNbt(itemMeta.getAsComponentString()));
                BaseComponent componentComponent = component.duplicate();
                BukkitMsgSender.INSTANCE.info(JsonHelper.getGson().toJson(itemComponent));
                BukkitMsgSender.INSTANCE.sendMsg(player, BukkitTextProcessor.setHoverEvent(componentComponent, new HoverEvent(HoverEvent.Action.SHOW_ITEM, itemComponent)));
            } else {
                BukkitMsgSender.INSTANCE.sendMsg(commandSender, component);
            }
        }

        private BaseComponent getItemNameComponent(ItemStack itemStack) {
            if (!itemStack.hasItemMeta()) {
                return BukkitTextProcessor.toTranslatableComponent(itemStack);
            }
            ItemMeta meta = itemStack.getItemMeta();
            if (!meta.hasDisplayName()) {
                return BukkitTextProcessor.toTranslatableComponent(itemStack);
            }
            return BukkitTextProcessor.toComponent(meta.getDisplayName());
        }
    };


}
