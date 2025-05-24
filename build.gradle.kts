plugins {
    alias(libs.plugins.android.application) apply false
}

buildscript {
    dependencies {
        // Google Services Plugin for Firebase
        classpath("com.google.gms:google-services:4.3.15")
    }
}