plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

apply {
    from("../ktlint.gradle.kts")
}

dependencies {

    implementation(Libs.stdLib)

    // JUnit
    testImplementation(Libs.jUnit)
}