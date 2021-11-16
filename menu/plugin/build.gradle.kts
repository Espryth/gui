plugins {
    id("com.github.johnrengelman.shadow") version("7.1.0")
}

dependencies {
    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")

    implementation(project(":menu:api"))
    implementation(project(":item:api"))

    arrayOf("1_8_R3").forEach {
        runtimeOnly(project(":menu:adapter:adapt-v$it"))
    }
}