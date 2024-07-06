import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val verName = extra["app.versionName"].toString()
val verCode = extra["app.versionCode"].toString().toInt()
val pkgName = "top.kagg886.mgt"
val appName = "音乐游戏训练"


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.buildConfigPlugin)
    alias(libs.plugins.kotlinSerializationJson)
}

buildConfig {
    packageName = pkgName
    buildConfigField("VER_NAME", verName)
    buildConfigField("VER_CODE", verCode)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "${pkgName}.common"
    generateResClass = always
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            /// Jetpack Media3 ExoPlayer
            implementation (libs.androidx.media3.exoplayer)
            implementation (libs.androidx.media3.ui)
            implementation (libs.androidx.media3.common)

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.precompose)
            implementation(libs.precompose.viewmodel)

            implementation(libs.kotlinx.serialization.json)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation("uk.co.caprica:vlcj:4.8.3")
        }
    }
}

android {
    namespace = pkgName
    compileSdk = 34

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = pkgName
        minSdk = 24
        targetSdk = 34
        versionCode = verCode
        versionName = verName
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.AppImage, TargetFormat.Exe)
            packageName = pkgName
            packageVersion = verName
        }
    }
}
