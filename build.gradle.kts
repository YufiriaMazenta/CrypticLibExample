
plugins {
    id("java-library")
    id("maven-publish")
    id("io.github.goooler.shadow").version("8.1.8")
    kotlin("jvm") version "2.4.10"
}

rootProject.group = rootProject.findProperty("group").toString()
rootProject.version = rootProject.findProperty("version")!!

repositories {
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
    //CrypticLib
    maven("https://repo.crypticlib.com/repository/maven-public/")
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.0.1")
    compileOnly(kotlin("stdlib"))
    implementation("com.crypticlib:bukkit:${rootProject.findProperty("crypticlibVersion")}")
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
        relocate("crypticlib", rootProject.findProperty("crypticlibRelocatePackage").toString())
        relocate("kotlin", "kotlin2420")
        relocate("org.intellij.lang.annotations", "example.libs.intellij.lang.annotations")
        relocate("org.jetbrains.annotations", "example.libs.jetbrains.annotations")
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

kotlin {
    jvmToolchain(21)
}