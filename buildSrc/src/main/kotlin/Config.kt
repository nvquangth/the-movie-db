object Android {
    const val applicationId = "com.example.cleanarchitecture"
    const val compileSdk = 30
    const val buildTools = "30.0.3"
    const val minSdk = 21
    const val targetSdk = 30
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
    const val baseUrlQa = "\"https://60ae0d5e80a61f00173324bc.mockapi.io\""

    const val develop = "dev"
    const val applicationIdDevelop = "com.example.cleanarchitecture.dev"
    const val versionCodeDevelop = 201
    const val versionNameDevelop = "2.0.1.DEV"
    const val baseUrlDevelop = "\"https://60ae0d5e80a61f00173324bc.mockapi.io\""

    const val staging = "stg"
    const val applicationIdStaging = "com.example.cleanarchitecture.stg"
    const val versionCodeStaging = 115
    const val versionNameStaging = "1.1.5.STG"
    const val baseUrlStaging = "\"https://60ae0d5e80a61f00173324bc.mockapi.io\""

    const val production = "prod"
    const val applicationIdProduction = "com.example.cleanarchitecture"
    const val versionCodeProduction = 100
    const val versionNameProduct = "1.0.0"
    const val baseUrlProduction = "\"https://60ae0d5e80a61f00173324bc.mockapi.io\""

    const val baseUrlParam = "BASE_URL"
}