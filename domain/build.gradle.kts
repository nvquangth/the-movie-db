plugins {
    id(GradlePlugins.javaLib)
    id(GradlePlugins.kotlin)
}

apply {
    from("../ktlint.gradle.kts")
}

dependencies {

    // Module
    implementation(project(Modules.entity))
    implementation(project(Modules.common))

    implementation(Libs.stdLib)

    // Hilt
    implementation(Libs.hiltDagger)

    // JUnit
    testImplementation(Libs.jUnit)

    // Mockito
    implementation(Libs.mockito)
    testImplementation(Libs.coroutinesTest)
}