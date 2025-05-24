plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.quizzapp_i438"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.quizzapp_i438"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Firebase BOM (Bill of Materials)
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))

    // Firebase Firestore
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Optional: Firebase Analytics (if needed)
    // implementation("com.google.firebase:firebase-analytics-ktx")
}

// Apply the Google Services plugin
apply(plugin = "com.google.gms.google-services")