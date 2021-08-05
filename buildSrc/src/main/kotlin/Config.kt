object Android {
    const val applicationId = "com.example.cleanarchitecture"
    const val compileSdk = 30
    const val buildTools = "30.0.3"
    const val minSdk = 21
    const val targetSdk = 30
    const val versionCode = 100
    const val versionName = "1.0.0"
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

abstract class ProductFlavor {

    companion object {
        const val baseUrl = "https://api.themoviedb.org"
    }

    abstract val name: String
    open val id: String = "com.example.cleanarchitecture"
    open val versionCode: Int = 100
    open val versionName: String = "1.0.0"
    open val baseUrl: String = "https://api.themoviedb.org"
}

class QaProductFlavor: ProductFlavor() {
    override val name: String = "qa"
}

class DevProductFlavor: ProductFlavor() {
    override val name: String = "dev"

}

class StagingProductFlavor: ProductFlavor() {
    override val name: String = "stg"

}

class ProductionProductFlavor: ProductFlavor() {
    override val name: String = "prod"

}

class ProductFlavorFactory {

    companion object {

        fun getProductFlavor(type: ProductFlavorType): ProductFlavor = when(type) {
            ProductFlavorType.QA -> QaProductFlavor()
            ProductFlavorType.DEV -> DevProductFlavor()
            ProductFlavorType.STAGING -> StagingProductFlavor()
            ProductFlavorType.PRODUCT -> ProductionProductFlavor()
        }
    }
}

enum class ProductFlavorType {
    QA,
    DEV,
    STAGING,
    PRODUCT
}
