import com.azaqaryan.buildsrc.Dependencies
import com.azaqaryan.buildsrc.Misc
import com.azaqaryan.buildsrc.Versions

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = Misc.appNameSpace
    compileSdk = Versions.Sdk.Compile
    defaultConfig {
        applicationId = Misc.appId
        minSdk = Versions.Sdk.Min
        targetSdk = Versions.Sdk.Target
        versionCode = Versions.App.Code
        versionName = Versions.App.Name
        testInstrumentationRunner = Misc.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = Misc.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL",
                "\"${Versions.Remote.BaseUrl}\""
            )
        }
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"${Versions.Remote.BaseUrl}\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = Versions.App.Jvm
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    //Main
    implementation(Dependencies.Kotlin.Coroutines.Core)
    implementation(Dependencies.Kotlin.Coroutines.Android)
    implementation(Dependencies.Google.Material)

    //AndroidX
    implementation(Dependencies.AndroidX.ConstraintLayout)
    implementation(Dependencies.AndroidX.AppCompat)
    implementation(Dependencies.AndroidX.Fragment)
    implementation(Dependencies.AndroidX.CoreKtx)
    implementation(Dependencies.AndroidX.Lifecycle.Runtime)
    implementation(Dependencies.AndroidX.Lifecycle.ViewModel)

    //Navigation
    implementation(Dependencies.AndroidX.Navigation.Fragment)
    implementation(Dependencies.AndroidX.Navigation.Ui)

    //Paging
    implementation(Dependencies.AndroidX.Paging)

    //Retrofit
    implementation(Dependencies.Retrofit.Core)
    implementation(Dependencies.Retrofit.ConverterGson)
    implementation(Dependencies.Retrofit.Interceptor.Logging)

    //Dagger
    implementation(Dependencies.Dagger.Core)
    kapt(Dependencies.Dagger.Compiler)

    //Coil: for image loading
    implementation(Dependencies.Coil.Core)

    //Tests
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

}