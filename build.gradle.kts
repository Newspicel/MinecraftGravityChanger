import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
}

plugins {
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "net.space-block.mc.gravity"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://papermc.io/repo/repository/maven-public/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
}

dependencies {
    compileOnly( "com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    implementation( group = "com.google.inject", name =  "guice", version = "5.0.1")

}

fun ShadowJar.relocate(pattern: String) {
    this.relocate(pattern, "${project.name}.$pattern")
}

fun ShadowJar.relocateFoundry(pattern: String) = relocate(pattern, "foundry.$pattern")

tasks {
    named<ShadowJar>("shadowJar") {
        archiveFileName.set("GravityChanger.jar")


        relocateFoundry("org.yaml.snakeyaml")
        relocateFoundry("org.jose4j")
        relocateFoundry("org.jetbrains.annotations")
        relocateFoundry("org.intellij.lang")
        relocateFoundry("org.apache.logging.log4j")
        relocateFoundry("org.apache.commons.lang3")
        relocateFoundry("org.apache.commons.io")
        relocateFoundry("org.apache.commons.codec")
        relocateFoundry("kotlin")
        relocateFoundry("it.unimi.dsi")
        relocateFoundry("com.mojang.util")
        relocateFoundry("com.fasterxml.jackson")
    }

    build {
        dependsOn(shadowJar)
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
