import java.io.FileInputStream
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.List
import kotlin.collections.arrayListOf
import kotlin.collections.forEach
import kotlin.collections.map

plugins {
    id(GradlePlugins.androidLib)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinKapt)
}

apply {
    from("../ktlint.gradle.kts")
}

android {
    compileSdkVersion(Android.targetSdk)

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)

        testInstrumentationRunner = AndroidJUnit.testInstrumentationRunner
    }

    buildTypes {
        getByName(BuildType.debug) {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"${ProductFlavor.baseUrl}\""
            )

            buildConfigField(
                "String",
                "API_KEY",
                getConfig()["API_KEY"] as String
            )

            buildConfigField(
                "String",
                "BASE_URL_AUTH",
                getConfig()["BASE_URL_AUTH"] as String
            )
        }

        getByName(BuildType.release) {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"${ProductFlavor.baseUrl}\""
            )

            buildConfigField(
                "String",
                "API_KEY",
                getConfig()["API_KEY"] as String
            )

            buildConfigField(
                "String",
                "BASE_URL_AUTH",
                getConfig()["BASE_URL_AUTH"] as String
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

fun getConfig(): Properties = Properties().apply {
    load(FileInputStream(File("config.properties")))
}

dependencies {

    // Module
    implementation(project(Modules.entity))
    implementation(project(Modules.domain))
    implementation(project(Modules.common))

    implementation(Libs.stdLib)

    // Android Core
    implementation(Libs.coreKtx)

    // Hilt
    implementation(Libs.hiltAndroid)
    implementation(Libs.hiltCore)
    kapt(Libs.hiltCompiler)

    // Room
    implementation(Libs.room)
    implementation(Libs.roomExt)
    kapt(Libs.roomProcessor)

    // Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.retrofitGson)

    // OkHttp
    implementation(Libs.okHttp)
    implementation(Libs.okHttpLogging)
    testImplementation(Libs.okHttpMockServer)

    // JUnit
    testImplementation(Libs.jUnit)
    androidTestImplementation(Libs.jUnitExt)
    androidTestImplementation(Libs.espresso)

    // Mockito
    implementation(Libs.mockito)
    testImplementation(Libs.coroutinesTest)

    // Robolectric
    testImplementation(Libs.robolectric)
}