plugins {
    id(GradlePlugins.androidApplication)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinKapt)
    id(GradlePlugins.navSafeArg)
    id(GradlePlugins.kotlinParcelize)
    id(GradlePlugins.hiltAndroid) // import latest
}

android {
    compileSdkVersion(Android.compileSdk)

    defaultConfig {
        applicationId = Android.applicationId

        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)

        versionCode = Android.versionCode

        testInstrumentationRunner = AndroidJUnit.testInstrumentationRunner

        multiDexEnabled = true
    }

    buildTypes {
        getByName(BuildType.debug) {
            isMinifyEnabled = BuildType.minifyDebug
            proguardFile(BuildType.proguardDebug)
        }

        getByName(BuildType.release) {
            isMinifyEnabled = BuildType.minifyRelease
            proguardFile(BuildType.proguardRelease)
        }
    }

    flavorDimensions("version")
    productFlavors {

        create("qa") {
            val flavor = ProductFlavorFactory.getProductFlavor(ProductFlavorType.QA)

            versionName = flavor.versionName
            versionNameSuffix = ".${flavor.name}"
            applicationIdSuffix = ".${flavor.name}"
        }

        create("dev") {
            val flavor = ProductFlavorFactory.getProductFlavor(ProductFlavorType.DEV)

            versionName = flavor.versionName
            versionNameSuffix = ".${flavor.name}"
            applicationIdSuffix = ".${flavor.name}"
        }

        create("stg") {
            val flavor = ProductFlavorFactory.getProductFlavor(ProductFlavorType.STAGING)

            versionName = flavor.versionName
            versionNameSuffix = ".${flavor.name}"
            applicationIdSuffix = ".${flavor.name}"
        }

        create("prod") {
            val flavor = ProductFlavorFactory.getProductFlavor(ProductFlavorType.STAGING)

            versionNameSuffix = ".${flavor.name}"
        }
    }

    variantFilter {
        when (name) {
            "qaRelease", "devRelease" -> ignore = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    // Module
    implementation(project(Modules.base))
    implementation(project(Modules.entity))
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.common))

    implementation(Libs.stdLib)

    // Activity
    implementation(Libs.activity)

    // Fragment
    implementation(Libs.fragment)

    // ConstraintLayout
    implementation(Libs.constraintLayout)

    // Appcompat
    implementation(Libs.appcompat)

    // Android Core
    implementation(Libs.coreKtx)

    // ViewModel + LiveData Lifecycle
    implementation(Libs.viewModel)
    implementation(Libs.liveData)
    implementation(Libs.lifecycleProcessorJava8)
    kapt(Libs.lifecycleProcessor)

    // Multidex
    implementation(Libs.multidex)

    // Navigation
    api(Libs.navigationFragment)
    api(Libs.navigationUi)

    // RecyclerView
    implementation(Libs.recyclerView)

    // Room
    implementation(Libs.room)
    implementation(Libs.roomExt)
    kapt(Libs.roomProcessor)

//    // ViewPager2
//    implementation(Libs.viewPager2)

    // Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.retrofitGson)

    // OkHttp
    implementation(Libs.okHttp)
    implementation(Libs.okHttpLogging)
    testImplementation(Libs.okHttpMockServer)

    // Glide
    implementation(Libs.glide)
    kapt(Libs.glideProcessor)

    // Hilt
    implementation(Libs.hiltAndroid)
    implementation(Libs.hiltCore)
    implementation(Libs.hiltNavigation)
    kapt(Libs.hiltCompiler)

    // JUnit
    testImplementation(Libs.jUnit)
    androidTestImplementation(Libs.jUnitExt)
    androidTestImplementation(Libs.espresso)

    // Mockito
    implementation(Libs.mockito)
    testImplementation(Libs.coroutinesTest)
    testImplementation(Libs.archTest)

    // Material Design
    implementation(Libs.material)

    // CardView
    implementation(Libs.cardView)

    // Preference
    implementation(Libs.preference)

    // Keyboard Listener
    implementation(Libs.keyboardListener)
}