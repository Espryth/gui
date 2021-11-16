rootProject.name = "gui"

include(":item:api")

arrayOf("api", "plugin", "adapter").forEach {
    include(":menu:$it")
}

arrayOf("1_8_R3").forEach {
    include(":menu:adapter:adapt-v$it")
}