package com.example.crypticlibexample.bungee;

import crypticlib.chat.BungeeMsgSender;
import crypticlib.command.BungeeCommand;
import crypticlib.command.BungeeSubCommand;
import crypticlib.command.CommandInfo;
import crypticlib.command.annotation.Command;
import crypticlib.command.annotation.Subcommand;
import crypticlib.perm.PermInfo;
import net.md_5.bungee.api.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Command
public class ExampleCommand extends BungeeCommand {

    public ExampleCommand() {
        super(new CommandInfo("test", new PermInfo("test.test"), new String[] {"tt"}));
    }

    @Subcommand
    BungeeSubCommand reload = new BungeeSubCommand("reload", new PermInfo("test.test.reload")) {
        @Override
        public boolean execute(@NotNull CommandSender sender, @NotNull List<String> args) {
            Example.INSTANCE.reloadPlugin();
            BungeeMsgSender.INSTANCE.sendMsg(sender, ExampleConfig.test.value());
            return true;
        }
        @Subcommand
        BungeeSubCommand test = new BungeeSubCommand("test2") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull List<String> args) {
                BungeeMsgSender.INSTANCE.sendMsg(sender, ExampleConfig.test2.value().toString());
                return true;
            }
        };
    };


}
