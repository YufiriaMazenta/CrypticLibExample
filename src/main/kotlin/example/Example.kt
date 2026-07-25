package example

import crypticlib.BukkitPlugin
import crypticlib.CrypticLibBukkit
import crypticlib.MinecraftVersion
import crypticlib.dependency.Dependency
import crypticlib.dependency.DependencyLoader
import crypticlib.util.IOHelper
import org.bukkit.event.Listener

class Example : BukkitPlugin(), Listener {

    init {
        try {
            DependencyLoader.INSTANCE.loadDependency(
                Dependency
                    .builder("org.jetbrains.kot#lin", "kot#lin-stdlib", "2.4.10")
                    .test("!kotlin.KotlinVersion")
                    .repository(Dependency.REPOSITORY_MAVEN_CENTRAL)
                    .repository(Dependency.REPOSITORY_MAVEN_CENTRAL_MIRROR_ALI)
                    .relocate("kot#lin", "kot#lin2410")
                    .relocate("org%intellij%lang%annotations", "example%libs%intellij%lang%annotations")
                    .relocate("org%jetbrains%annotations", "example%libs%jetbrains%annotations")
                    .build()
            )
        } catch (e: Throwable) {
            throw RuntimeException(e)
        }
        INSTANCE = this
    }

    override fun whenEnable() {
        IOHelper.info("Server Type: " + CrypticLibBukkit.serverAdapter().type().name)
        IOHelper.info("Server Version: " + MinecraftVersion.CURRENT.name)
    }

    override fun whenDisable() {
        IOHelper.info("Plugin disabled.")
    }

    companion object {
        lateinit var INSTANCE: Example
    }

}