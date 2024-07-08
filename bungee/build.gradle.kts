import java.text.SimpleDateFormat

repositories {
    mavenLocal()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("http://repo.crypticlib.com:8081/repository/maven-public/") {
        isAllowInsecureProtocol = true
    }
    mavenCentral()
}

dependencies {
    compileOnly("net.md-5:bungeecord-api:1.21-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.0.1")
    implementation("com.crypticlib:bungee:${rootProject.findProperty("crypticlibVersion")}")
}

var mainClass = "com.example.crypticlibexample.bungee.Example"
var pluginVersion: String = version.toString() + "-" + SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis())

tasks {
    val props = HashMap<String, String>()
    props["version"] = pluginVersion
    props["main"] = mainClass
    props["name"] = rootProject.name
    processResources {
        filesMatching("bungee.yml") {
            expand(props)
        }
    }
}