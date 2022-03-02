rootProject.name = "gui"

includePrefixed("api")

fun includePrefixed(name: String) {
    include("gui-$name")
    project(":gui-$name").projectDir = file(name)
}