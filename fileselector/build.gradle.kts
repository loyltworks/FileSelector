plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-android-extensions")
    id("maven-publish")
}

android {
    namespace = "com.loyltworks.fileselector"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    afterEvaluate {
        publishing {
            publications {
                create<MavenPublication>("release") {
                    from(components["release"])
                    groupId = "com.github.loyltworks"
                    artifactId = "LwsFileSelector"
                    version = "1.0.1"
                }
            }
        }
    }

    buildFeatures{
        viewBinding=true 
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Kotlin Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.0")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.0")

    // CameraX core library using camera2 implementation
    implementation ("androidx.camera:camera-camera2:1.1.0-alpha01")
    // CameraX Lifecycle Library
    implementation ("androidx.camera:camera-lifecycle:1.1.0-alpha01")
    // CameraX View class
    implementation ("androidx.camera:camera-view:1.0.0-alpha21")
}