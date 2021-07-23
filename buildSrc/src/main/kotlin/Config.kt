object Versions {
    // Kotlin
    // https://github.com/JetBrains/kotlin/releases
    // https://kotlinlang.org/releases.html
    const val kotlin = "1.4.10"

    // Android Gradle Plugin
    // https://developer.android.com/studio/releases/gradle-plugin
    const val androidBuildGradle = "4.0.0"

    // ConstraintLayout
    // https://developer.android.com/jetpack/androidx/releases/constraintlayout
    const val constraintLayout = "2.0.2"

    // Appcompat
    // https://developer.android.com/jetpack/androidx/releases/appcompat
    const val appcompat = "1.2.0"

    // Activity
    // https://developer.android.com/jetpack/androidx/releases/activity
    const val activity = "1.1.0"

    // Fragment
    // https://developer.android.com/jetpack/androidx/releases/fragment
    const val fragment = "1.2.5"

    // Android Core
    // https://developer.android.com/jetpack/androidx/releases/core
    const val coreKtx = "1.3.2"

    // ViewModel + LiveData Lifecycle
    // https://developer.android.com/jetpack/androidx/releases/lifecycle
    const val lifecycle = "2.2.0"

    // Multidex
    // https://developer.android.com/jetpack/androidx/releases/multidex
    const val multidex = "2.0.1"

    // Arch Core
    // https://developer.android.com/jetpack/androidx/releases/arch-core
    const val archCore = "2.1.0"

    // Navigation
    // https://developer.android.com/jetpack/androidx/releases/navigation
    const val navigation = "2.3.1"

    // RecyclerView
    // https://developer.android.com/jetpack/androidx/releases/recyclerview
    const val recyclerView = "1.1.0"

    // Room
    // https://developer.android.com/jetpack/androidx/releases/room
    const val room = "2.2.5"

    // ViewPager2
    // https://developer.android.com/jetpack/androidx/releases/viewpager2
    const val viewPager2 = "1.0.0"

    // CardView
    // https://developer.android.com/jetpack/androidx/releases/cardview
    const val cardView = "1.0.0"

    // Preference
    // https://developer.android.com/jetpack/androidx/releases/preference
    const val preference = "1.1.1"

    // Retrofit
    // https://github.com/square/retrofit/releases
    const val retrofit = "2.9.0"

    // OkHttp
    // https://github.com/square/okhttp/releases
    const val okHttp = "4.9.0"

    // Glide
    // https://github.com/bumptech/glide/releases
    const val glide = "4.11.0"

    // Hilt
    // https://developer.android.com/jetpack/androidx/releases/hilt
    const val hilt = "2.28-alpha"
    const val hiltAndroid = "1.0.0-alpha02"

    // JUnit
    // https://junit.org/junit5/docs/current/release-notes/index.html
    const val jUnit = "4.12"
    const val jUnitExt = "1.1.1"
    const val espresso = "3.2.0"

    // Mockito
    // https://mvnrepository.com/artifact/org.mockito/mockito-core
    const val mockito = "3.3.3"

    // Material Design
    // https://github.com/material-components/material-components-android/releases
    const val material = "1.2.1"

    // Timber Logging
    // https://github.com/JakeWharton/timber/releases
    const val timber = "4.7.1"

    // Easy Permission
    // https://github.com/googlesamples/easypermissions/releases
    const val easyPermission = "3.0.0"

    // Gson
    // https://github.com/google/gson/releases
    const val gson = "2.8.6"

    // Coroutines test
    // https://github.com/Kotlin/kotlinx.coroutines/releases
    const val coroutinesTest = "1.3.9"

    // Robolectric
    // https://github.com/robolectric/robolectric
    const val robolectric = "4.4"
}

object GradlePlugins {
    const val androidApplication = "com.android.application"
    const val androidLib = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExt = "kotlin-android-extensions"
    const val javaLib = "java-library"
    const val kotlin = "kotlin"
    const val kotlinKapt = "kotlin-kapt"
    const val hiltAndroid = "dagger.hilt.android.plugin"
    const val navSafeArg = "androidx.navigation.safeargs.kotlin"
}

object BuildPlugins {
    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidBuildGradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val navSafeArg =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Libs {
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    // ConstraintLayout
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Appcompat
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    // Activity
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"

    // Fragment
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    // Android Core
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    // ViewModel
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    // LiveData
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    // Annotation Processor
    const val lifecycleProcessor = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    // Annotation Processor Lifecycle for Java 8
    const val lifecycleProcessorJava8 =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"

    // Multidex
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    // Navigation
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navDynamicFeature =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"

    // Architecture
    const val archCore = "androidx.arch.core:core-common:${Versions.archCore}"
    const val archRuntime = "androidx.arch.core:core-runtime:${Versions.archCore}"
    const val archTest = "androidx.arch.core:core-testing:${Versions.archCore}"

    // RecyclerView
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    // Room
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomProcessor = "androidx.room:room-compiler:${Versions.room}"

    // Room extension and Coroutines support
    const val roomExt = "androidx.room:room-ktx:${Versions.room}"

    // ViewPager2
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    // OkHttp
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    // for testing
    const val okHttpMockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideProcessor = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Hilt
    const val hiltDagger = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltDaggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltAndroid}"
    const val hiltAndroidProcessor = "androidx.hilt:hilt-compiler:${Versions.hiltAndroid}"

    // JUnit
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    // Mockito
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"

    // Coroutines Test
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"

    // Material Design
    const val material = "com.google.android.material:material:${Versions.material}"

    // CardView
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"

    // Preference
    const val preference = "androidx.preference:preference-ktx:${Versions.preference}"

    // Timber Logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // Easy Permission
    const val easyPermission = "pub.devrel:easypermissions:${Versions.easyPermission}"

    // Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
}

object Modules {
    const val base = ":base"
    const val entity = ":entity"
    const val domain = ":domain"
    const val data = ":data"
    const val common = ":common"
}

object Android {
    const val applicationId = "com.example.cleanarchitecture"
    const val compileSdk = 29
    const val buildTools = "29.0.3"
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 100
    const val versionNam = "1.0.0"
}

object AndroidJUnit {
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildType {
    const val debug = "debug"
    const val minifyDebug = false
    const val proguardDebug = "proguard-rule.pro"

    const val release = "release"
    const val minifyRelease = false
    const val proguardRelease = "proguard-release.pro"
}

object ProductFlavor {

    const val qa = "qa"
    const val applicationIdQa = "com.example.cleanarchitecture.qa"
    const val versionCodeQa = 201
    const val versionNameQa = "2.0.1.QA"
    const val baseUrlQa = "\"https://api.themoviedb.org\""

    const val develop = "dev"
    const val applicationIdDevelop = "com.example.cleanarchitecture.dev"
    const val versionCodeDevelop = 201
    const val versionNameDevelop = "2.0.1.DEV"
    const val baseUrlDevelop = "\"https://api.themoviedb.org\""

    const val staging = "stg"
    const val applicationIdStaging = "com.example.cleanarchitecture.stg"
    const val versionCodeStaging = 115
    const val versionNameStaging = "1.1.5.STG"
    const val baseUrlStaging = "\"https://api.themoviedb.org\""

    const val production = "prod"
    const val applicationIdProduction = "com.example.cleanarchitecture"
    const val versionCodeProduction = 100
    const val versionNameProduct = "1.0.0"
    const val baseUrlProduction = "\"https://api.themoviedb.org\""

    const val baseUrlParam = "BASE_URL"
}
