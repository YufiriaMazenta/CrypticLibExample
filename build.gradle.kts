plugins {
    id("java-library")
    id("maven-publish")
    id("io.github.goooler.shadow").version("8.1.8")
}

rootProject.group = rootProject.findProperty("group").toString()
rootProject.version = rootProject.findProperty("version")!!
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

repositories {
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.crypticlib.com:8081/repository/maven-public/")
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.0.1")
    implementation("com.crypticlib:bukkit:${rootProject.findProperty("crypticlibVersion")}")
    implementation("com.crypticlib:bukkit-ui:${rootProject.findProperty("crypticlibVersion")}")
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks {
    compileJava {
        dependsOn(clean)
        options.encoding = "UTF-8"
    }
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        relocate("crypticlib", "${rootProject.group}.${rootProject.name.lowercase()}.crypticlib")
        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
    }
    val props = HashMap<String, String>()
    props["version"] = rootProject.version.toString()
    props["main"] = rootProject.findProperty("main").toString()
    props["name"] = rootProject.name
    processResources {
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}
