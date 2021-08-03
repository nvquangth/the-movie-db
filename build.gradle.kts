buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(kotlin(module = "gradle-plugin", version = Versions.kotlin))
        classpath(BuildPlugins.androidPlugin)
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.hiltPlugin)
        classpath(BuildPlugins.navSafeArg)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
