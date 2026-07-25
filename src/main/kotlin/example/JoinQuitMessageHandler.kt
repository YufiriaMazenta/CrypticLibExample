package example

import crypticlib.chat.BukkitTextProcessor
import crypticlib.chat.PaperTextProcessor
import crypticlib.listener.EventListener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

@EventListener
object JoinQuitMessageHandler {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.joinMessage(
            PaperTextProcessor.deserializeLegacyText(
                BukkitTextProcessor.placeholder(
                    event.player, "&8[&a+&8] &7%player_name%"
                )
            )
        )
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        event.quitMessage(
            PaperTextProcessor.deserializeLegacyText(
                BukkitTextProcessor.placeholder(
                    event.player, "&8[&c-&8] &7%player_name%"
                )
            )
        )
    }

}