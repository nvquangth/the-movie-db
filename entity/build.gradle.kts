plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

apply {
    from("../ktlint.gradle.kts")
}

dependencies {

    // Module
    implementation(project(Modules.common))

    implementation(Libs.stdLib)
}