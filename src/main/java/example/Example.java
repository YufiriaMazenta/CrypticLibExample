package example;

import crypticlib.BukkitPlugin;
import crypticlib.CrypticLibBukkit;
import crypticlib.MinecraftVersion;
import crypticlib.dependency.Dependency;
import crypticlib.dependency.DependencyLoader;
import crypticlib.dependency.Repository;
import crypticlib.util.IOHelper;
import org.bukkit.event.Listener;

public final class Example extends BukkitPlugin implements Listener {

    public Example() {
        try {
            DependencyLoader.INSTANCE.loadDependency(
                Dependency
                    .builder("org.jetbrains.kot#lin", "kot#lin-stdlib", "2.4.10")
                    .test("!kot#lin2420%Kot#linVersion")
                    .repository(Repository.MAVEN_CENTRAL_MIRROR_ALI)
                    .repository(Repository.MAVEN_CENTRAL)
                    .relocate("kot#lin", "kot#lin2420")
                    .relocate("org%intellij%lang%annotations", "example%libs%intellij%lang%annotations")
                    .relocate("org%jetbrains%annotations", "example%libs%jetbrains%annotations")
                    .build()
            );
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void whenEnable() {
        IOHelper.info("Server Type: " + CrypticLibBukkit.serverAdapter().type().name());
        IOHelper.info("Server Version: " + MinecraftVersion.CURRENT.name());
    }

    @Override
    public void whenDisable() {
        IOHelper.info("Plugin disabled.");
    }

}