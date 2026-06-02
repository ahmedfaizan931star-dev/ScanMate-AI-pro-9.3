import java.util.Locale
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

val compileSdkOverride = providers.gradleProperty("SCANMATE_COMPILE_SDK").orElse("35").get().toInt()
val targetSdkOverride = providers.gradleProperty("SCANMATE_TARGET_SDK").orElse("35").get().toInt()
val versionCodeOverride = (System.getenv("VERSION_CODE") ?: providers.gradleProperty("VERSION_CODE").orElse("5").get()).toInt()
val versionNameOverride = System.getenv("VERSION_NAME") ?: providers.gradleProperty("VERSION_NAME").orElse("1.5.0").get()
val signingProperties = Properties().apply {
    val signingFile = rootProject.file("keystore.properties")
    if (signingFile.exists()) {
        signingFile.inputStream().use { input -> load(input) }
    }
}
val releaseKeystorePath = System.getenv("KEYSTORE_PATH")
    ?: signingProperties.getProperty("storeFile")
    ?: (project.findProperty("storeFile") as String?)
    ?: "keystore/scanmate-release.jks"
val releaseStorePassword = System.getenv("KEY_STORE_PASSWORD")
    ?: System.getenv("STORE_PASSWORD")
    ?: signingProperties.getProperty("storePassword")
    ?: (project.findProperty("storePassword") as String?)
val releaseKeyAlias = System.getenv("KEY_ALIAS")
    ?: signingProperties.getProperty("keyAlias")
    ?: (project.findProperty("keyAlias") as String?)
    ?: "scanmate-key"
val releaseKeyPassword = System.getenv("KEY_PASSWORD")
    ?: signingProperties.getProperty("keyPassword")
    ?: (project.findProperty("keyPassword") as String?)
val hasReleaseSigning = !releaseKeystorePath.isNullOrBlank() &&
    !releaseStorePassword.isNullOrBlank() &&
    !releaseKeyPassword.isNullOrBlank()

android {
    namespace = "com.synthbyte.scanmate"
    compileSdk = compileSdkOverride

    defaultConfig {
        applicationId = "com.synthbyte.scanmate"
        minSdk = 24
        targetSdk = targetSdkOverride
        versionCode = versionCodeOverride
        versionName = versionNameOverride

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    signingConfigs {
        create("release") {
            storeFile = file(releaseKeystorePath)
            storePassword = releaseStorePassword ?: ""
            keyAlias = releaseKeyAlias
            keyPassword = releaseKeyPassword ?: ""
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isDebuggable = true
        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            isCrunchPngs = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-Xjvm-default=all",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += setOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/INDEX.LIST"
            )
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.coil.compose)
    implementation(libs.converter.moshi)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.zxing.core)
    implementation(libs.hilt.android)
    implementation(libs.androidx.biometric)
    implementation(libs.androidx.security.crypto)
    implementation("com.tom-roush:pdfbox-android:2.0.27.0")
   implementation("org.apache.poi:poi-ooxml-lite:5.2.5")

    implementation("com.itextpdf:itextg:5.5.10")
    implementation("com.madgag.spongycastle:core:1.58.0.0")
    implementation("com.madgag.spongycastle:prov:1.58.0.0")
    implementation("com.madgag.spongycastle:bcpkix-jdk15on:1.58.0.0")

    implementation("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")
    implementation("com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0")

    testImplementation(libs.androidx.compose.ui.test.junit4)
    testImplementation(libs.androidx.core)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.robolectric)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.kotlinx.coroutines.test)

    debugImplementation(libs.logging.interceptor)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    ksp(libs.androidx.room.compiler)
    ksp(libs.moshi.kotlin.codegen)
    kapt(libs.hilt.compiler)
    kaptTest(libs.hilt.compiler)
    kaptAndroidTest(libs.hilt.compiler)
}
