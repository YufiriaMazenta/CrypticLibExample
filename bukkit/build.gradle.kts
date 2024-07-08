import java.text.SimpleDateFormat

repositories {
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("http://repo.crypticlib.com:8081/repository/maven-public/") {
        isAllowInsecureProtocol = true
    }
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.0.1")
    implementation("com.crypticlib:bukkit:${rootProject.findProperty("crypticlibVersion")}")
}

var mainClass = "com.example.crypticlibexample.bukkit.Example"
var pluginVersion: String = version.toString() + "-" + SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis())

tasks {
    val props = HashMap<String, String>()
    props["version"] = pluginVersion
    props["main"] = mainClass
    props["name"] = rootProject.name
    processResources {
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}