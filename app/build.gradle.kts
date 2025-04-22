import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

val apiKey: String = localProperties.getProperty("APIKey") ?: ""
val baseURL: String = localProperties.getProperty("BaseURL") ?: ""
val imageBaseURL: String = localProperties.getProperty("ImageBaseURL") ?: ""

android {
    namespace = "com.mdi.movie"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mdi.movie"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // Read the API key from local.properties

        buildConfigField("String", "TMDB_API_KEY", "\"$apiKey\"")
        buildConfigField("String", "BASE_URL", "\"$baseURL\"")
        buildConfigField("String", "IMAGE_BASE_URL", "\"$imageBaseURL\"")


    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    //Android X Main Libs
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    //Material - UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material3) // or latest
    //Navigation
    implementation(libs.androidx.navigation.compose)
    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    //Collections
    implementation(libs.kotlinx.collections.immutable)
    //Image Loading
    implementation(libs.coil.compose)
    //Test
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.junit.jupiter)
    testImplementation(libs.androidx.core.testing)

    implementation(libs.github.glide)
    implementation(libs.androidx.viewbinding)
    implementation(libs.androidx.security.crypto)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)
    implementation(libs.emoji.google)
    implementation(libs.google.webrtc)
    implementation(libs.socket.io)
    implementation(libs.shimmerlayout)

    implementation("com.google.android.material:material:1.11.0") // Material 2 for views
    implementation("androidx.compose.material3:material3:1.2.1")

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))

}